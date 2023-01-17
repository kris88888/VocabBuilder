package com.vocab.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.Instant
import java.util.*

const val WORDS_TABLE_NAME = "word_tbl"

@Entity(tableName = WORDS_TABLE_NAME)
data class WordEntry(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "word")
    val word: String,

    @ColumnInfo(name = "word_meaning")
    val meaning: String,

    @ColumnInfo(name = "usage")
    val usage: String,

    @ColumnInfo(name = "lastUpdated")
    val lastUpdatedDt: Date = Date.from(Instant.now())
)