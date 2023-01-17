package com.vocab.data.datasource

import com.vocab.data.model.db.WordEntry

class WordListDataSource {

    fun loadData():List<WordEntry> {
        return listOf(
            WordEntry(word = "Word1", meaning = "Meaning1", usage = "Example Usage1"),
            WordEntry(word = "Word2", meaning = "Meaning2", usage = "Example Usage2"),
            WordEntry(word = "Word3", meaning = "Meaning3", usage = "Example Usage3"),
            WordEntry(word = "Word4", meaning = "Meaning4", usage = "Example Usage4"),
            WordEntry(word = "Word5", meaning = "Meaning5", usage = "Example Usage5"),
            WordEntry(word = "Word6", meaning = "Meaning6", usage = "Example Usage6"),
            WordEntry(word = "Word7", meaning = "Meaning7", usage = "Example Usage7"),
            WordEntry(word = "Word8", meaning = "Meaning8", usage = "Example Usage8"),
            WordEntry(word = "Word9", meaning = "Meaning9", usage = "Example Usage9"),
            WordEntry(word = "Word10", meaning = "Meaning10", usage = "Example Usage10")
        )
    }
}