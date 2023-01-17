package com.vocab.data.repository

import com.vocab.data.dao.WordDatabaseDao
import com.vocab.data.model.db.WordEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WordRepository @Inject constructor(val dao: WordDatabaseDao) {

    suspend fun addWord(word:WordEntry) = dao.addWord(word)
    suspend fun deleteWord(word:WordEntry) = dao.deleteWord(word)
    suspend fun modifyWord(word:WordEntry) = dao.update(word)
    suspend fun deleteAll() = dao.deleteAll()
    fun getAllWords(): Flow<List<WordEntry>> =
        dao.getAllWords().flowOn(Dispatchers.IO).conflate()
}