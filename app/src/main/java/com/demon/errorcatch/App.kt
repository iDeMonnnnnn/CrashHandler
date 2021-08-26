package com.demon.errorcatch

import android.app.Application
import com.demon.errorinfocatch.CrashHandler

/**
 * @author DeMon
 * @date 2018/8/14
 * @description
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val crashHandler = CrashHandler.getInstance()
        crashHandler.init(applicationContext)
    }
}