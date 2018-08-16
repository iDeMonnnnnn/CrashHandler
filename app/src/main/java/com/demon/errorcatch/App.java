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
         // 注册crashHandler
        // 异常处理，不需要处理时注释掉这两句即可！
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
