package com.example.picvault.data.local.database.converters

import androidx.room.TypeConverter
import com.example.picvault.data.model.Url

class UrlToStringConverter {
        @TypeConverter
        fun fromUrl(url: Url): String {
            return url.toString()
        }

        @TypeConverter
        fun toUrl(url: String): Url {
            return Url(url)
        }
    }
