package com.demon.errorcatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demon.errorinfocatch.DLog;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DLog dLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.d("debug");
        Logger.e("error");
        Logger.w("warning");
        Logger.v("verbose");
        Logger.i("information");
        Logger.wtf("What a Terrible Failure");
    }
}
