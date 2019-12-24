package com.cily.utils.logFile.file;

import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.Logs;
import com.cily.utils.base.time.TimeType;
import com.cily.utils.base.time.TimeUtils;
import com.cily.utils.logFile.Output;
import com.cily.utils.logFile.queue.LogQueue;
import com.cily.utils.logFile.task.WriteFileThreadPool;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Administrator on 2019/6/22.
 * 写文件
 */

public class WriteFileRunnable implements Runnable {
    private String fileDir;
    private String fileName;
    private FileWriter fileWriter;
    private long maxFileSize = 1024 * 1024;
    private int maxFileCount = 3;

    public void setMaxFileCount(int maxFileCount) {
        if (maxFileCount < 1){
            maxFileCount = 3;
        }
        this.maxFileCount = maxFileCount;
    }

    public void setMaxFileSize(long maxFileSize) {
        if (maxFileSize <= 0){
            maxFileSize = 1024 * 1024;
        }
        this.maxFileSize = maxFileSize;
    }

    public WriteFileRunnable(String fileDir, String fileName){
        this.fileDir = fileDir;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        writeFile(LogQueue.getInatnce().poll());
    }

    public void writeFile(String str){
        if (!StrUtils.isEmpty(str)){
            if (fileWriter == null){

                Output.println("--fileWriter--" + fileWriter);
                initPrintWriter();
            }
            if (fileWriter == null){
                return;
            }

            try {
                fileWriter.write(str);
                fileWriter.flush();
            } catch (IOException e) {
                Logs.printException(e);
            }
            long fileSize = getFileSize();
            Output.println("fileSize = " + fileSize);
            if (fileSize >= maxFileSize){
                fileName = TimeUtils.milToStr(System.currentTimeMillis(), TimeType.SECOND_LINE_UNDER) + ".log";
                release();
            }
        }
    }

    public void delFiles(){
        if (!StrUtils.isEmpty(fileDir)){
            File f = new File(fileDir);
            if (f != null && f.exists()) {
                File[] fs = f.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String s) {
                        Output.println("--->" + file.isFile() + "--->" + s);
                        return s != null && s.endsWith(".log");
                    }
                });
                Output.println("--->" + fs.length);
                if (fs == null || fs.length <= maxFileCount) {
                    return;
                }

                Arrays.sort(fs, new Comparator<File>() {
                    @Override
                    public int compare(File file, File t1) {
                        long r = file.lastModified() - t1.lastModified();
                        if (r > 0){
                            return 1;
                        }else if (r < 0){
                            return -1;
                        }
                        return 0;
                    }
                });
                int fileCount = fs.length;
                int num = 0;
                for (File file : fs){
                    if (file != null && file.exists() && file.isFile()){
                        if (!StrUtils.isEmpty(file.getAbsolutePath()) && file.getAbsolutePath().endsWith(".log")){
                            Output.println("文件路径：" + file.getAbsolutePath() + "<--->文件大小：" + file.length()
                                    + "<--->最后修改时间：" + TimeUtils.milToStr(file.lastModified(), null));
                            if (num < fileCount - maxFileCount) {
                                Output.println("被删除的文件名称：" + file.getName());
                                file.delete();
                                num++;
                            }
                            Output.println("剩余文件数量：" + fs.length + "<---> num = " + num);
                        }
                    }
                }

                Output.println("剩余文件数量--->" + fs.length);
            }
        }
    }

    public void initPrintWriter(){
        if (StrUtils.isEmpty(fileName)){
            return;
        }

        Output.println("fileName = " + fileName);

        if (fileWriter == null){
            try {
                delFiles();

                if (!StrUtils.isEmpty(fileDir)) {
                    fileWriter = new FileWriter(fileDir + File.separator + fileName, true);
                }else {
                    fileWriter = new FileWriter(fileName, true);
                }
            } catch (IOException e) {
                Logs.printException(e);
            }
        }
    }

    public void release(){
        if (fileWriter != null){
            try {
                fileWriter.close();
            } catch (IOException e) {
                Logs.printException(e);
            }
        }
        fileWriter = null;
    }

    public long getFileSize(){
        String fp = null;
        if (!StrUtils.isEmpty(fileDir)){
            fp = fileDir + File.separator + fileName;
        }else {
            fp = fileName;
        }
        if (StrUtils.isEmpty(fp)){
            return 0;
        }
        File f = new File(fp);
        if (f.exists() && f.isFile()){
            return f.length();
        }
        return 0;
    }
}
