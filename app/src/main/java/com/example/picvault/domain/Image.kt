package com.example.picvault.domain

import com.example.picvault.data.model.Url

data class Image(var uri: Url, var timeStamp: Long = System.currentTimeMillis())
