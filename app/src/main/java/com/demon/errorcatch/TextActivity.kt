package com.demon.errorcatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demon.errorinfocatch.DFileUtils
import kotlinx.android.synthetic.main.activity_text.*

class TextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        val path = intent.getStringExtra("path")

        tv_content.text = DFileUtils.readText(path)

    }
}