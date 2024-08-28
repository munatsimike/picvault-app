package com.example.picvault.domain

import com.example.picvault.data.model.ImageEntity

// Convert an Image object to ImageEntity object to be stored in the local database
fun Image.toImageEntity(): ImageEntity {
    return ImageEntity(id = this.id, uri = this.uri, timeStamp = this.timeStamp)
}

// converts a list of Image objects to a list of ImageEntity objects
fun List<Image>.toListOfImageEntity(): List<ImageEntity>{
   return this.map { image -> image.toImageEntity() }
}