package com.demon.errorinfocatch;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * @author DeMon
 * @date 2018/8/14
 * @description
 */
public class FileUtil {
    /**
     * 获取文件夹路径
     *
     * @param context
     * @param pathName
     * @return
     */
    public static String getPath(Context context, String pathName) {
        String path = null;
        try {
            path = Environment.getExternalStorageDirectory().getCanonicalPath() + "/" + context.getResources().getString(R.string.app_name) + "/" + pathName + "/";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static  String getCrashFile(Context mContext) {
        return FileUtil.getPath(mContext, "Crash");
    }
}
