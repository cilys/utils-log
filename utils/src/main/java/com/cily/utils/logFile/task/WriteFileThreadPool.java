package com.cily.utils.logFile.task;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.TimerUtils;
import com.cily.utils.base.time.TimeType;
import com.cily.utils.base.time.TimeUtils;
import com.cily.utils.logFile.Output;
import com.cily.utils.logFile.file.WriteFileRunnable;
import com.cily.utils.logFile.file.WriteLogFileRunnable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/6/22.
 * 写文件的线程池
 */

public class WriteFileThreadPool {
    private ScheduledExecutorService pool;
    private WriteFileRunnable writeFileRunnable;

    private WriteFileThreadPool(){
        initThreadPool();
    }

    private static WriteFileThreadPool instance;

    public static WriteFileThreadPool getInstance(){
        if (instance == null){
            synchronized (WriteFileThreadPool.class){
                if (instance == null){
                    instance = new WriteFileThreadPool();
                }
            }
        }
        return instance;
    }

    private void initThreadPool(){
        writeFileThreadRunning = false;

        if (pool == null){
            pool = Executors.newScheduledThreadPool(1);
        }
    }

    public WriteFileThreadPool initWriteRunnable(String fileDir, String fileName){
        if (writeFileRunnable == null){
            if (StrUtils.isEmpty(fileName)){
                fileName = TimeUtils.milToStr(System.currentTimeMillis(), TimeType.SECOND_LINE_UNDER) + ".log";
            }
            writeFileRunnable = new WriteLogFileRunnable(fileDir, fileName, 300);
        }
        return this;
    }

    public void start(){
        if (pool == null){
            initThreadPool();
        }
        Output.println("pool = " + pool);
        Output.println("writeFileRunnable = " + writeFileRunnable);
        Output.println("writeFileThreadRunning = " + writeFileThreadRunning);
        if (pool != null && writeFileRunnable != null) {
            if (writeFileThreadRunning){
                return;
            }
            pool.scheduleAtFixedRate(writeFileRunnable, 3, 3, TimeUnit.SECONDS);
            writeFileThreadRunning = true;
        }
    }

    private boolean writeFileThreadRunning = false;

    public void shutdown(){
        writeFileThreadRunning = false;

        if (pool != null){
            pool.shutdown();
        }
        pool = null;

        if (writeFileRunnable != null){
            writeFileRunnable.release();
        }
    }
}
