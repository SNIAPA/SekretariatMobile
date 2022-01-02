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
import java.io.File
import java.io.FileInputStream
import java.net.URI
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var downloader: Downloader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloader =  Downloader()
        downloader.downloadFile(this,"https://pastebin.com/raw/64WC2PdT","asd.json")



        this.registerReceiver(
            attachmentDownloadCompleteReceive, IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE
            )
        )

    }

    var attachmentDownloadCompleteReceive: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                val downloadId = intent.getLongExtra(
                    DownloadManager.EXTRA_DOWNLOAD_ID, 0
                )
                val pfd =  downloader.dm.openDownloadedFile(downloadId) as ParcelFileDescriptor
                Log.d("path", downloader.dm.getUriForDownloadedFile(downloadId).path !!)
                val file = File(downloader.dm.getUriForDownloadedFile(downloadId).path!!)


                val gsonBuilder = GsonBuilder()
                gsonBuilder.registerTypeAdapter(Date::class.java, DateDeserializer())
                val school = gsonBuilder.create().fromJson(file.readText(), School::class.java)



                pfd.close()
            }
        }
    }
}