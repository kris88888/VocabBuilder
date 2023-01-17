package com.vocab.data.model.db

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {

    @TypeConverter
    fun toLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(time:Long) : Date {
        return Date(time)
    }
}