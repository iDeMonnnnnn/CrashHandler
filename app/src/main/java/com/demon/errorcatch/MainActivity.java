package com.demon.errorcatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.demon.errorinfocatch.CrashHandler;
import com.demon.errorinfocatch.DLog;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DLog dLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] strings = CrashHandler.getCrashReportFiles(this);
        for (int i = 0; i < strings.length; i++) {
            Log.i(TAG, "onCreate: " + strings[i]);
        }

        findViewById(R.id.bug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + dLog.toString());
            }
        });
    }
}
