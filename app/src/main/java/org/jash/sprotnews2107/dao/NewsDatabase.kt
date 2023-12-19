package org.jash.sprotnews2107.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.jash.sprotnews2107.entity.Category
import org.jash.sprotnews2107.entity.News

@Database(entities = [ News::class ], version = 3, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun getNewsDao():NewsDao
    abstract fun getUserDao():UserDao
}