package com.demon.errorcatch

import android.content.Intent
import android.content.pm.ApplicationInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demon.errorcatch.R
import com.demon.errorinfocatch.CrashHandler
import com.demon.errorcatch.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*
import java.io.File
import java.lang.Exception
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val datas = mutableListOf<String>()

    private var adapter: Adapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = Adapter()
        rv.adapter = adapter

        initData()

        bug1.setOnClickListener {
            val list: List<String> = ArrayList()
            Log.i(TAG, "bug1: " + list[1]) //模拟数组越界
        }
        bug2.setOnClickListener {
            val info: ApplicationInfo? = null
            val name = info!!.name
        }
    }


    private fun initData() {
        val strings = CrashHandler.getCrashReportFiles(this).toList() //获取异常文件路径
        for (s in strings) {
            Log.i(TAG, "initData: $s")
        }
        datas.clear()
        datas.addAll(strings)
        adapter?.notifyDataSetChanged()
    }


    inner class Adapter : RecyclerView.Adapter<Adapter.VH>() {


        inner class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
            VH(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

        override fun onBindViewHolder(holder: VH, position: Int) {
            val file = File(datas[position])
            holder.itemView.run {
                tv_name.text = file.name
                btn_del.setOnClickListener {
                    file.delete()
                    datas.removeAt(position)
                    notifyDataSetChanged()
                }
                btn_lookup.setOnClickListener {
                    val intent = Intent(this@MainActivity, TextActivity::class.java)
                    intent.putExtra("path", datas[position])
                    startActivity(intent)
                }
            }
        }

        override fun getItemCount(): Int = datas.size
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}