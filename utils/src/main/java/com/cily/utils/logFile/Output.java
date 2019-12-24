package com.cily.utils.logFile;

/**
 * Created by Administrator on 2019/7/17.
 */

public class Output {
    private static boolean debugLog = false;

    public static void setDebugLog(boolean debugLog) {
        Output.debugLog = debugLog;
    }

    public static boolean isDebugLog() {
        return debugLog;
    }

    public static void println(String s) {
        if (isDebugLog()) {
            System.out.println(s);
        }
    }
}
