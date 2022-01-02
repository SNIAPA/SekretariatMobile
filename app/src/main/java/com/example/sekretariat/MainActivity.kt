package com.example.sekretariat

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import com.google.gson.GsonBuilder
import java.net.URI
import java.util.*
import kotlin.collections.ArrayList
import android.app.Activity
import com.google.gson.Gson
import java.io.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fileContent:String;
        Thread {
            val urls = ArrayList<String>() //to read each line
            try {
                val url = URL("https://pastebin.com/raw/2RWvKSBh")
                val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
                conn.setConnectTimeout(60000) // timing out in a minute
                val `in` = BufferedReader(InputStreamReader(conn.getInputStream()))

                var str: String
                while (`in`.readLine().also { str = it } != null) {
                    urls.add(str)
                }
                `in`.close()
            } catch (e: Exception) {
                Log.d("MyTag", e.toString())
            }

            fileContent = urls[0]
            Log.d("fileContent",fileContent)
            Gson().fromJson(fileContent,School::class.java)
        }.start()




    }


}