package com.example.picvault.data.model

import android.net.Uri

class Url(uriString: String) {

    // Parse and validate the URI
    private val uri: Uri = Uri.parse(uriString)

    init {
        if (!isValidUri(uri)) {
            throw IllegalArgumentException("Invalid URI: $uriString")
        }
    }

    // Add validation logic inside the class
    private fun isValidUri(uri: Uri): Boolean {
        return try {
            uri.scheme != null && uri.isAbsolute
        } catch (e: Exception) {
            false
        }
    }
}