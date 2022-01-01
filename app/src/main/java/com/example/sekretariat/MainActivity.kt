package com.example.sekretariat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.FileInputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val manager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(Uri.parse("https://pastebin.com/pJ5sGujL"))

        val reference: Long = manager.enqueue(request)
        val file = manager.openDownloadedFile(reference) as ParcelFileDescriptor
        Log.d("uri", file.getFileDescriptor().toString())
    }
}