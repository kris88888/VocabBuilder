package com.vocab.builder.di

import android.content.Context
import androidx.room.Room
import com.vocab.data.WORDS_DATA_BASE
import com.vocab.data.WordDatabase
import com.vocab.data.dao.WordDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Provide dependencies to Hilt for injection usage.
// Hilt would search for the modules while scanning for deps.
// Every object provided in this module can be injected in the singleton scope.

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesWordDao(database: WordDatabase): WordDatabaseDao =
        database.wordDao()

    @Singleton
    @Provides
    fun providesAppDatabase(@ApplicationContext context: Context): WordDatabase {
        return Room.databaseBuilder(context, WordDatabase::class.java, WORDS_DATA_BASE)
            .fallbackToDestructiveMigration().build()
    }

}