package com.demon.errorcatch;

import android.app.Application;

import com.demon.errorinfocatch.CrashHandler;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;

/**
 * @author DeMon
 * @date 2018/8/14
 * @description
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });
        String model = android.os.Build.MODEL;
        String carrier = android.os.Build.MANUFACTURER;
        FormatStrategy formatStrategy = CsvFormatStrategy.newBuilder()
                .tag(model)
                .build();
        Logger.addLogAdapter(new DiskLogAdapter(formatStrategy));
        // 注册crashHandler
        // 异常处理，不需要处理时注释掉这两句即可！
        /*CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());*/
    }
}
