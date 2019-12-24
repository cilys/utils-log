package com.cily.utils.logFile.file;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.time.TimeUtils;
import com.cily.utils.logFile.Output;
import com.cily.utils.logFile.queue.LogQueue;
import com.cily.utils.logFile.task.WriteFileThreadPool;

import java.io.IOException;

/**
 * Created by Administrator on 2019/6/22.
 */

public class WriteLogFileRunnable extends WriteFileRunnable {
    //每次从队列里取30条数据出来
    private int count = 30;

    //连续读取空数据的次数
    private int nullCount = 0;

    public WriteLogFileRunnable(String fileDir, String fileName) {
        this(fileDir, fileName, 30);
    }

    public WriteLogFileRunnable(String fileDir, String fileName, int count) {
        super(fileDir, fileName);

        if (count <= 0){
            count = 30;
        }
        this.count = count;
    }

    @Override
    public void run() {
        Output.println(TimeUtils.milToStr(System.currentTimeMillis(), null) + " nullCount = " + nullCount);
        StringBuffer su = new StringBuffer();
        for (int i = 0; i < count; i++) {
            String s = LogQueue.getInatnce().poll();
            if (StrUtils.isEmpty(s)) {
                break;
            } else {
                su.append(s);
                su.append("\n");
            }
        }
        /*System.out.println("修改前：" + count);
        if (LogQueue.getInatnce().size() > count){
            count = 2 * count;
            if (count > 3000){
                count = 3000;
            }
        }else {
            count = count / 2;
            if (count < 1){
                count = 30;
            }
        }
        System.out.println("修改后：" + count);
        */
        String str = su.toString();
        if (StrUtils.isEmpty(str)){
            nullCount ++;
        }else {
            nullCount = 0;
            writeFile(str);
        }

        if (nullCount >= 3){
            Output.println(TimeUtils.milToStr(System.currentTimeMillis(), null) + " nullCount = " + nullCount + "<--->shutdown");
            WriteFileThreadPool.getInstance().shutdown();
        }
    }

    @Override
    public void release() {
        super.release();
        nullCount = 0;
    }
}
