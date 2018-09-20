package com.demon.errorcatch;

import android.app.Application;

import com.demon.errorinfocatch.CrashHandler;

/**
 * @author DeMon
 * @date 2018/8/14
 * @description
 */
public class App extends Application {
    private static final String TAG = "App";
    @Override
    public void onCreate() {
        super.onCreate();
        String model = android.os.Build.MODEL;
        String carrier = android.os.Build.MANUFACTURER;
        // 注册crashHandler
        // 异常处理，不需要处理时注释掉这两句即可！
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
