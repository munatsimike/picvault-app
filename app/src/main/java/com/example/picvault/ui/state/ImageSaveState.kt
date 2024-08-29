package com.example.picvault.ui.state

import com.example.picvault.data.model.Url
import com.example.picvault.ui.error.ErrorMassage

sealed class ImageSaveState {
    data class Failure(val errorMassage: ErrorMassage): ImageSaveState()
    data object Success: ImageSaveState()
    data object Initial: ImageSaveState()
}