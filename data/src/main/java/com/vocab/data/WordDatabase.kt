package com.vocab.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vocab.data.dao.WordDatabaseDao
import com.vocab.data.model.db.DateTypeConverter
import com.vocab.data.model.db.WordEntry

const val WORDS_DATA_BASE = "words_db"

@Database(entities = [WordEntry::class], version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)

abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDatabaseDao
}