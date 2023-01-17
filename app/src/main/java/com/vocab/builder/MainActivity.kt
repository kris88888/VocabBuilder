package com.vocab.builder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vocab.builder.ui.screens.NoteScreen
import com.vocab.builder.ui.theme.VocabBuilderTheme
import com.vocab.builder.viewmodels.WordScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

// Annotation that indicates that it would be accessing dependencies through DI.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val wordsViewModel: WordScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wordsViewModel.getAllNotes()
        setContent {
            VocabBuilderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WordsApp(wordsViewModel)
                }
            }
        }
    }

    @Composable
    fun WordsApp(viewModel: WordScreenViewModel) {
        val wordsList = viewModel.wordsList.collectAsState().value

        NoteScreen(
            wordsList = wordsList,
            onAddWord = { word -> viewModel.addWord(word) },
            onRemoveWord = { word ->
                viewModel.removeWord(word)
                           }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VocabBuilderTheme {

    }
}