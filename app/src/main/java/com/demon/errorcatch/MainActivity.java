package com.demon.errorcatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demon.errorinfocatch.CrashHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> list = new ArrayList<>();

        String[] strings = CrashHandler.getCrashReportFiles(this);
        for (int i = 0; i < strings.length; i++) {
            Log.i(TAG, "onCreate: " + strings[i]);
        }
        Log.i(TAG, "onCreate: " + list.get(1));
    }
}
