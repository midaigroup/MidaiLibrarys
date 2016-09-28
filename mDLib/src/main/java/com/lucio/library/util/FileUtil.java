package com.lucio.library.util;

import android.os.Environment;

public class FileUtil {

    /**
     * 获取外置卡路径
     * 
     * @return
     */
    public static String getExternalStorageDirectory() {
        if (!isExternalStorageAvailable()) {
            return null;
        }
        return Environment.getExternalStorageDirectory().toString();
    }

    /**
     * 判断外置卡是否可用
     * 
     * @return
     */
    public static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
    
    /**
     * 获取外部存储根目录
     * 存储卡可用返回存储卡路径反之返回手机存储路径
     * @return
     */
    public static String getRootFileDirs() {
        String filesPath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            filesPath = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();
        } else {
            filesPath = Environment.getRootDirectory().getAbsolutePath();
        }
        return filesPath;
    }
}
