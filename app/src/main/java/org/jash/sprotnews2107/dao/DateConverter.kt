package org.jash.sprotnews2107.dao

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateConverter {
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    @TypeConverter
    fun toCreateTime(date: Date):String = sdf.format(date)
    @TypeConverter
    fun fromCreateTime(string: String):Date = sdf.parse(string)
}