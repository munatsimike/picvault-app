package com.example.picvault.ui

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File

object Gallery {
    @Composable
    fun GalleryScreen(context: Context) {
        val urls = getStoredImageUris(context)
        LazyVerticalGrid(contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(urls) { url -> DisplayCapturedImage(imageUri = url) }
        }
    }


    private fun getStoredImageUris(context: Context): List<Uri> {
        // Get the directory where images are stored
        val imagesDir = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "")

        // List all image files in the directory
        val imageFiles = imagesDir.listFiles { file ->
            // Filter files by image extensions
            file.isFile && (file.extension == "jpg" || file.extension == "jpeg" || file.extension == "png")
        } ?: emptyArray()

        // Convert file paths to URIs using FileProvider
        return imageFiles.map { file ->
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider", // Ensure this matches the authority in your manifest
                file
            )
        }
    }

    @Composable
    fun DisplayCapturedImage(imageUri: Uri?) {
        if (imageUri != null) {
            Card(Modifier.aspectRatio(4f/3f), shape = RoundedCornerShape(0.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(imageUri),
                    contentDescription = "Captured Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        } else {
            Text("No image captured yet.")
        }
    }
}