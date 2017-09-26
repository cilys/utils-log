package com.cily.utils.l;

import android.content.Context;

import com.cily.utils.encrypt.MD5Utils;

/**
 * user:cily
 * time:2017/9/1
 * desc:
 */

public class A {
    public static void init(Context cx){
        try{
            MD5Utils.check(cx);
        }catch (Exception e){
//            e.printStackTrace();
        }
    }
}
