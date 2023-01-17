package com.vocab.data.dao

import androidx.room.*
import com.vocab.data.model.db.WORDS_TABLE_NAME
import com.vocab.data.model.db.WordEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@Dao
interface WordDatabaseDao {
    @Query("select * from $WORDS_TABLE_NAME")
    fun getAllWords(): Flow<List<WordEntry>>

    @Query("select * from $WORDS_TABLE_NAME where id=:id")
    suspend fun getWordById(id: String): WordEntry

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWord(word: WordEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
   suspend fun update(word:WordEntry)

    @Query("DELETE from $WORDS_TABLE_NAME")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteWord(note:WordEntry)
}