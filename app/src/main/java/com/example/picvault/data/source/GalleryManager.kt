import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * GalleryManager is responsible for managing media-related tasks,
 * specifically saving images to the device's gallery. This class interacts
 * with the Android MediaStore to insert images into the user's public media storage.
 */
class GalleryManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    /**
     * Saves the given bitmap image to the device's gallery.
     *
     * @param bitmap The Bitmap image to be saved.
     * @return The URI of the saved image if successful, or null if the operation fails.
     */
    fun saveImageToGallery(bitmap: Bitmap): Uri? {
        // Get the content resolver to interact with the MediaStore
        val resolver = context.contentResolver

        // Create content values to describe the image file's metadata
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "my_image_${System.currentTimeMillis()}.jpg") // Sets the file name with a timestamp
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg") // Sets the file type as JPEG
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES) // Sets the file path to the Pictures directory
        }

        // Insert the image into the MediaStore and get the resulting URI
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        // If the URI is not null, open an output stream and write the bitmap data to it
        uri?.let {
            resolver.openOutputStream(it)?.use { outputStream ->
                // Compress the bitmap into JPEG format and save it to the output stream
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
        }

        return uri
    }
}
