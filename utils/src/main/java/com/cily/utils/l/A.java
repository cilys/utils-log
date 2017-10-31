package com.cily.utils.l;

import android.content.Context;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.Sys;
import com.cily.utils.encrypt.MD5Utils;

/**
 * user:cily
 * time:2017/9/1
 * desc:
 */

public class A {
    public static void init(Context cx){
        try{
//            MD5Utils.check(cx);
            if (cx != null){
                String pn = cx.getPackageName();
                String getPn = Lt.getPn();
                if (StrUtils.isEmpty(pn) || StrUtils.isEmpty(getPn)){

                }else {
                    if (pn.equals(getPn) || pn.contains(getPn)){

                    }else {
                        Sys.exit();
                    }
                }
            }
        }catch (Exception e){
//            e.printStackTrace();
        }
    }
}
