package com.glogachev.developerslife.ui.pages

import android.content.Context
import android.os.Handler
import android.os.Looper
import okhttp3.*
import pl.droidsonroids.gif.GifDrawable
import java.io.File
import java.io.IOException

class GifLoader(private val context: Context) {
    private val client = OkHttpClient()
    private val handler = Handler(Looper.getMainLooper())
    fun load(url: String, block: (GifDrawable) -> Unit) {
        val name = url.split("/").last()
        val request = Request.Builder()
            .url(url)
            .build()
        val fileR = File(context.filesDir, name)
        if (fileR.exists()) {
            val gif = GifDrawable(fileR)
            handler.post {
                block(gif)

            }
        } else {
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                }

                override fun onResponse(call: Call, response: Response) {

                    val outputStream = context.openFileOutput(name, Context.MODE_PRIVATE)

                    response.body?.byteStream()?.copyTo(outputStream)
                    outputStream.flush()
                    outputStream.close()

                    val file = File(context.filesDir, name)

                    val gif = GifDrawable(file)
                    handler.post {
                        block(gif)
                    }
                }
            })
        }
    }
}