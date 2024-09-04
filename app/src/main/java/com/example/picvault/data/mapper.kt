package com.example.picvault.data

import com.example.picvault.data.model.ImageEntity
import com.example.picvault.domain.Image

// Converts an ImageEntity object to an Image object for UI consumption
fun ImageEntity.toDomainImage(): Image{
    return Image(uri = this.uri, timeStamp =  this.timeStamp)
}

// Converts a list of ImageEntity objects to a list of Image objects
fun List<ImageEntity>.toListOfImage(): List<Image>{
  return  this.map { imageEntity -> imageEntity.toDomainImage() }
}