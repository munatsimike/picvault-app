package com.example.picvault


import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.picvault.data.model.Url
import com.example.picvault.domain.Image
import com.example.picvault.ui.Gallery.GalleryScreen
import com.example.picvault.ui.PicViewModel
import com.example.picvault.ui.component.ExtendedFOBButton
import com.example.picvault.ui.state.ImageSaveState
import com.example.picvault.ui.theme.PicVaultTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var photoUri: Uri

    // Inject the ViewModel using Hilt's extension function
    private val picViewModel: PicViewModel by viewModels()

    // Register for activity result to handle capturing a photo
    private val capturePhotoLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            // Image successfully captured and stored in the specified location
            println("Image captured successfully: $photoUri")
            savePhotoUriToRoom(photoUri)
        } else {
            // Handle failure or cancellation
            println("Image capture failed or was cancelled.")
        }
    }

    private fun savePhotoUriToRoom(photoUri: Uri) {
        val photoEntity = Image(Url(uriString = photoUri.toString()))
        // Insert into Room database using your DAO
        lifecycleScope.launch {
            picViewModel.saveToGallery(photoEntity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Request camera permission using the new API
        requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        setContent {
            MainScreen(context = this) { captureImage() }
        }
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

@Composable
fun MainScreen(context: Context, picViewModel: PicViewModel = hiltViewModel(), captureImage: () -> Unit) {
    val snackBarHostState = remember { SnackbarHostState() }
    // Observe state from the ViewModel inside the Composable
    val saveState by picViewModel.message.collectAsState()
    LaunchSnackBar(snackBarHostState, saveState)
    PicVaultTheme {
        Scaffold(
            floatingActionButton = { ExtendedFOBButton { captureImage() } },
            floatingActionButtonPosition = FabPosition.End,
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            content = { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    GalleryScreen(context)
                }
            }
        )
    }
}

@Composable
fun LaunchSnackBar(snackBarHostState: SnackbarHostState, saveState: ImageSaveState) {
    val currentSaveState = rememberUpdatedState(saveState)

    LaunchedEffect(currentSaveState.value) {

        when (saveState) {
            is ImageSaveState.Failure -> {
                snackBarHostState.showSnackbar(
                    message = "Failed to save image.",
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Short
                )
            }

            is ImageSaveState.Success -> {
                snackBarHostState.showSnackbar(
                    message = "Image saved successfully!",
                    duration = SnackbarDuration.Short
                )
            }

            ImageSaveState.Initial -> {
                // No snackbar for initial state
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PicVaultTheme {
    }
}
