package com.cily.utils.logFile.queue;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.Logs;
import com.cily.utils.logFile.task.WriteFileThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/6/22.
 * 待写入文件的队列
 */

public class LogQueue {

    private LogQueue(){
        blockingQueue = new LinkedBlockingDeque<>();

    }

    private static LogQueue logQueue;

    public static LogQueue getInatnce(){
        if (logQueue == null){
            synchronized (LogQueue.class){
                if (logQueue == null){
                    logQueue = new LogQueue();
                }
            }
        }
        return logQueue;
    }

    private BlockingQueue<String> blockingQueue;

    public void put(String str){
        if (StrUtils.isEmpty(str)){
            return;
        }
        try {
            blockingQueue.put(str);

            WriteFileThreadPool.getInstance().start();
        } catch (Throwable e) {
            Logs.printException(e);
        }
    }

    public String poll(){
        try {
            return blockingQueue.poll(1, TimeUnit.SECONDS);
        } catch (Throwable e) {
            Logs.printException(e);
        }
        return null;
    }

    public int size(){
        return blockingQueue == null ? 0 : blockingQueue.size();
    }

}
