package com.example.picvault.ui

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.example.picvault.R
import com.example.picvault.ui.component.DisplayScreenHeader
import com.example.picvault.ui.component.HeaderData
import com.example.picvault.ui.util.titles
import java.io.File
import kotlin.random.Random

object Gallery {

    @Composable
    fun GalleryScreen(context: Context, headerData: HeaderData, modifier: Modifier = Modifier) {
        val urls = getStoredImageUris(context)
        var isTitleVisible by remember { mutableStateOf(true) }
        val gridState = rememberLazyGridState()

        // Detect the scroll offset to toggle title visibility
        LaunchedEffect(gridState) {
            snapshotFlow { gridState.firstVisibleItemScrollOffset }
                .collect { offset ->
                    isTitleVisible = gridState.firstVisibleItemIndex == 0 && offset < 100
                }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            DisplayScreenHeader(headerData = headerData, isTitleVisible = isTitleVisible)
            DisplayGridImages(state = gridState, urls)
        }
    }

    @Composable
    private fun DisplayGridImages(state: LazyGridState, urls: List<Uri>) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            state = state
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
            Card(
                modifier = Modifier.aspectRatio(4f / 3f),
                shape = RoundedCornerShape(0.dp)
            ) {
                Box {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Captured Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    Box(
                        contentAlignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.5f),
                                        Color.Transparent
                                    ),
                                    startY = 0f,
                                    endY = 100f
                                )
                            )
                    ) {
                        ImageTitle(title = titles[Random.nextInt(titles.size)])
                    }
                }
            }
        } else {
            Text("No image captured yet.")
        }
    }

    @Composable
    fun ImageTitle(title: String, modifier: Modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(IntrinsicSize.Min)
                .padding(5.dp)
                .background(Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp) // This padding is now applied to the entire Row
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.house_24px),
                contentDescription = "House icon",
                tint = Color.White,
                modifier = Modifier.size(20.dp) // Modifier is applied to the Icon specifically
            )
            Spacer(modifier = Modifier.width(8.dp)) // Add spacing between the icon and the text
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
            )
        }
    }
}