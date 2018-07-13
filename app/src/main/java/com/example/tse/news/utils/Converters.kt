package com.example.tse.news.utils

import android.arch.persistence.room.TypeConverter
import java.util.*

class Converters {
    // No es necesario hacer ninguna configuracion especial solo colocas la anotacion en la clase ArticleDatabase
    // y hace automatico la conversion.

    @TypeConverter
    fun fromTimestamp(date: Long?): Date? {
        return if (date == null) null else Date(date)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}