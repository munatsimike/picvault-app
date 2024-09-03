package com.example.picvault.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.picvault.domain.Image
import com.example.picvault.domain.useCase.SaveImageUseCase
import com.example.picvault.ui.error.ErrorMassage
import com.example.picvault.ui.state.ImageSaveState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PicViewModel @Inject constructor(private val saveImageUseCase: SaveImageUseCase) :
    ViewModel() {

    private val message_ = mutableStateOf<ImageSaveState>(ImageSaveState.Initial)
    val message: State<ImageSaveState> = message_

    fun saveToGallery(image: Image) {
        val result = saveImageUseCase.saveImageToGallery(image)

        if (result.isSuccess) {
            message_.value = ImageSaveState.Success
        } else {
            val errorMsg = result.exceptionOrNull()?.message ?: "Unknown error has occurred"
            print(errorMsg)
            message_.value = ImageSaveState.Failure(ErrorMassage("Image not saved"))
        }
    }


}