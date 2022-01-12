package com.demon.errorinfocatch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DeMon
 * @date 2018/8/14
 * @description
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashHandler";
    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    /**
     * 程序的Context对象
     */
    private Context mContext;
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".log";

    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (CrashHandler.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     *
     * @param ctx
     */
    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        handleException(ex);
        if (mDefaultHandler != null) {
            //收集完信息后，交给系统自己处理崩溃
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }

    /**
     * 自定义错误处理,收集错误信息
     * 发送错误报告等操作均在此完成.
     * 开发者可以根据自己的情况来自定义异常处理逻辑
     */
    private void handleException(Throwable ex) {
        if (ex == null) {
            Log.w(TAG, "handleException--- ex==null");
            return;
        }
        //保存错误报告文件
        saveCrashInfoToFile(ex);
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return
     */
    private void saveCrashInfoToFile(Throwable ex) {
        try {
            Writer info = new StringWriter();
            PrintWriter printWriter = new PrintWriter(info);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            String result = info.toString();
            printWriter.close();
            StringBuilder sb = new StringBuilder();
            String now = sdf.format(new Date());
            File logFile = new File(getCrashFilePath(mContext) + now + CRASH_REPORTER_EXTENSION);
            if (!logFile.exists()) {
                logFile.createNewFile();
                //程序信息
                sb.append("\nAPPLICATION_ID:").append(mContext.getPackageName());//软件APPLICATION_ID
                sb.append("\nVERSION_CODE:").append(BuildConfig.VERSION_CODE + "");//软件版本号
                sb.append("\nVERSION_NAME:").append(BuildConfig.VERSION_NAME);//VERSION_NAME
                sb.append("\nBUILD_TYPE:").append(BuildConfig.BUILD_TYPE);//是否是DEBUG版本
                //设备信息
                sb.append("\nMODEL:").append(Build.MODEL);
                sb.append("\nRELEASE:").append(Build.VERSION.RELEASE);
                sb.append("\nSDK:").append(Build.VERSION.SDK_INT);
                sb.append("\n");
            }
            //崩溃信息
            sb.append("\nTIME:").append(now);//崩溃时间
            sb.append("\nEXCEPTION:").append(Log.getStackTraceString(ex));
            sb.append("\nSTACK_TRACE:").append(result);
            sb.append("\n");
            DFileUtils.writeTxt(logFile.getAbsolutePath(), sb.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件夹路径
     *
     * @param context
     * @return
     */
    public static String getCrashFilePath(Context context) {
        String path = context.getExternalFilesDir(null) + "/Crash/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    /**
     * 获取错误报告文件路径
     *
     * @param ctx
     * @return
     */
    public static String[] getCrashReportFiles(Context ctx) {
        File filesDir = new File(getCrashFilePath(ctx));
        String[] fileNames = filesDir.list();
        int length = fileNames.length;
        String[] filePaths = new String[length];
        for (int i = 0; i < length; i++) {
            filePaths[i] = filesDir.getAbsolutePath() + "/" + fileNames[i];
        }
        return filePaths;
    }
}
