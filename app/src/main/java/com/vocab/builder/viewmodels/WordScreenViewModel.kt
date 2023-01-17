package com.vocab.builder.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vocab.data.model.db.WordEntry
import com.vocab.data.repository.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordScreenViewModel @Inject constructor(val repository: WordRepository) : ViewModel() {

    private val _wordList = MutableStateFlow<List<WordEntry>>(emptyList())
    val wordsList = _wordList.asStateFlow()

    /**
     * Adds word to database.
     */
    fun addWord(wordEntry: WordEntry) {
        viewModelScope.launch {
            repository.addWord(wordEntry)
        }
    }

    fun updateWord(wordEntry: WordEntry) {
        viewModelScope.launch {
            repository.modifyWord(wordEntry)
        }
    }

    fun removeWord(wordEntry: WordEntry) {
        viewModelScope.launch {
            repository.deleteWord(wordEntry)
        }
    }

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllWords().distinctUntilChanged()
                .collect {
                    if (it.isEmpty()) {
                        Log.d(TAG, "getAllNotes: Empty Lust")
                        _wordList.value = it
                    } else {
                        _wordList.value = it
                    }
                }
        }
    }
}