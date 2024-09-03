package com.example.picvault


import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.example.picvault.ui.Gallery.GalleryScreen
import com.example.picvault.ui.theme.PicVaultTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var photoUri: Uri

    // Register for activity result to handle capturing a photo
    private val capturePhotoLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            // Image successfully captured and stored in the specified location
            println("Image captured successfully: $photoUri")
        } else {
            // Handle failure or cancellation
            println("Image capture failed or was cancelled.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Request camera permission using the new API
        requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)

        setContent {
            PicVaultTheme {
                Scaffold(
                    floatingActionButton = { ExtendedExample { captureImage() } },
                    floatingActionButtonPosition = FabPosition.End,
                    content = { paddingValues ->
                        Box {
                            Modifier.padding(paddingValues)
                            GalleryScreen(this@MainActivity)
                        }
                    })
            }
        }
    }

    @Composable
    fun ExtendedExample(onClick: () -> Unit) {
        ExtendedFloatingActionButton(
            onClick = { onClick() },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = "",
                    modifier = Modifier.size(35.dp),
                    tint = Color.Unspecified
                )
            },
            text = { Text(text = "Camera") },
        )
    }

    // Request camera permission using the Activity Result API
    private val requestCameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission granted
            println("Camera permission granted")
        } else {
            // Permission denied
            println("Camera permission denied")
        }
    }

    // Function to initiate the image capture
    private fun captureImage() {
        // Create a file for saving the image
        val photoFile = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "IMG_${System.currentTimeMillis()}.jpg"
        )

        // Get the content URI for the file using FileProvider
        photoUri = FileProvider.getUriForFile(
            this,
            "${BuildConfig.APPLICATION_ID}.provider", // Ensure this matches the authority in your manifest
            photoFile
        )

        // Launch the default camera app to capture the image
        capturePhotoLauncher.launch(photoUri)
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PicVaultTheme {
    }
}