package com.cily.utils.log;

import android.util.Log;

import com.cily.utils.base.HttpUtils;
import com.cily.utils.base.StrUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cily
 * @version 1.0.0   2017-01-23  提交到服务器
 */
public class PostError {
    private final String TAG = this.getClass().getSimpleName();

    /**
     * post提交
     * @param logUrl    log服务器地址
     * @param header    请求头
     * @param debug     是否调试
     */
    public void post(String logUrl, Map<String, String> header, boolean debug){
        if (StrUtils.isEmpty(logUrl)){
            return;
        }
        List<LogBean> l = DbUtils.search(10);
        if (l == null || l.size() < 1){
            return;
        }
        JSONArray ja = new JSONArray();
        for (LogBean b : l){
            JSONObject jo = new JSONObject();
            Field[] fs = b.getClass().getDeclaredFields();
            if (fs != null){
                for (Field f : fs){
                    try {
                        jo.put("\"" + f.getName() + "\"", f.get(f.getName()));
                    } catch (JSONException e1) {
                        logEx(debug, "JSONException", e1);
                    } catch (IllegalAccessException e1) {
                        logEx(debug, "IllegalAccessException", e1);
                    }
                }
            }
            ja.put(jo);
        }
        if (ja == null || ja.length() < 1){
            return;
        }
        Map<String, String>param = new HashMap<>();
        param.put(HttpUtils.JSON_BODY, ja.toString());
        try {
            String res = HttpUtils.post(logUrl, false, header, param);
            if (res != null){
                for (LogBean b : l) {
                    DbUtils.del(b);
                }
            }
        } catch (IOException e1) {
            logEx(debug, "http post errorLog error!!!", e1);
        }
    }

    private void logEx(boolean debug, String msg, Throwable t){
        if (t == null){
            return;
        }
        if (debug){
            Log.e(TAG, "" + msg, t);
        }
    }
}
