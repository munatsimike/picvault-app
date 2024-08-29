package com.example.picvault.data.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.picvault.domain.Image
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton


class ImageConverter @Inject constructor(private val context: Context) {

    fun imageToBitmap(image: Image): Bitmap? {
        return try {
            val uriString = image.uri.toString()
            val uri = Uri.parse(uriString)

            // Handle local URIs
            if (uri.scheme == "file" || uri.scheme == "content") {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream)
                }
            } else {
                // Handle network URIs
                val inputStream = URL(uriString).openStream()
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}