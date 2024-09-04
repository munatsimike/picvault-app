package com.example.picvault.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.picvault.domain.Image
import com.example.picvault.domain.useCase.SaveImageUseCase
import com.example.picvault.ui.error.ErrorMassage
import com.example.picvault.ui.state.ImageSaveState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PicViewModel @Inject constructor(private val saveImageUseCase: SaveImageUseCase) :
    ViewModel() {

    private val message_ = MutableStateFlow<ImageSaveState>(ImageSaveState.Initial)
    val message: StateFlow<ImageSaveState> = message_

    suspend fun saveToGallery(image: Image) {

        val result = saveImageUseCase.saveImageToGallery(image)

        if (result.isSuccess) {
            message_.value = ImageSaveState.Success
        } else {
            val errorMsg = result.exceptionOrNull()?.message ?: "Unknown error has occurred"
            Log.e("error",errorMsg)
            message_.value = ImageSaveState.Failure(ErrorMassage("Image not saved"))
        }
        resetMessage()
    }

    private suspend fun resetMessage() {
        delay(1000)
        if (message_.value != ImageSaveState.Initial) {
            message_.value = ImageSaveState.Initial
        }
    }
}