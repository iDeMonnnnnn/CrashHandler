package com.demon.errorcatch;

import android.app.Application;

import com.demon.errorinfocatch.CrashHandler;

/**
 * @author DeMon
 * @date 2018/8/14
 * @description
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
