package com.example.sekretariat

import android.widget.Toast


import androidx.core.content.FileProvider

import android.webkit.MimeTypeMap

import android.os.Environment

import android.app.Activity
import android.app.DownloadManager
import android.content.*
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.FileInputStream
import java.lang.IllegalStateException
import com.google.gson.Gson
import java.lang.reflect.Type
import kotlin.reflect.typeOf
import com.google.gson.GsonBuilder
import java.util.*


class  Downloader{

    lateinit var dm:DownloadManager


    fun downloadFile(activity: Activity, url: String?, fileName: String?) {
        try {
            if (url != null && !url.isEmpty()) {
                val uri: Uri = Uri.parse(url)
                val request: DownloadManager.Request = android.app.DownloadManager.Request(uri)
                request.setMimeType(getMimeType(uri.toString()))
                request.setTitle(fileName)
                request.setDescription("Downloading attachment..")
                request.allowScanningByMediaScanner()
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                dm = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                dm.enqueue(request)
            }
        } catch (e: IllegalStateException) {
            Toast.makeText(
                activity,
                "Please insert an SD card to download file",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Used to get MimeType from url.
     *
     * @param url Url.
     * @return Mime Type for the given url.
     */
    private fun getMimeType(url: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            val mime = MimeTypeMap.getSingleton()
            type = mime.getMimeTypeFromExtension(extension)
        }
        return type
    }

    /**
     * Attachment download complete receiver.
     *
     *
     * 1. Receiver gets called once attachment download completed.
     * 2. Open the downloaded file.
     */

}