package com.vocab.builder.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vocab.builder.R
import com.vocab.builder.ui.components.CommonButton
import com.vocab.builder.ui.components.TextInputComponent
import com.vocab.data.datasource.WordListDataSource
import com.vocab.data.model.db.WordEntry
import java.util.*

private const val TAG = "NoteScreen"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteScreen(
    wordsList: List<WordEntry>,
    onAddWord: (WordEntry) -> Unit,
    onRemoveWord: (WordEntry) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        addTopBar()
        addInputForm(onAddWord)
        Divider(modifier = Modifier.padding(10.dp))

        if (wordsList.isEmpty()) {
            EmptyRow()
        } else {
            LazyColumn {

                items(wordsList, { word: WordEntry -> word.id }) { word ->
                    val rememberedDismissState = rememberDismissState()

                    if (rememberedDismissState.isDismissed(DismissDirection.StartToEnd)) {
                        onRemoveWord(word)
                    }

                    SwipeToDismiss(
                        state = rememberedDismissState,
                        modifier = Modifier.padding(vertical = Dp(1f)),
                        directions = setOf(DismissDirection.StartToEnd),
                        dismissThresholds = { FractionalThreshold(0.20f) },
                        background = {
                            val color by animateColorAsState(
                                targetValue = if (rememberedDismissState.targetValue == DismissValue.Default) {
                                    Color.Transparent
                                } else {
                                    Color.LightGray
                                }
                            )
                            val icon = Icons.Default.Delete

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 20.dp)
                                    .background(color),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Delete Word",
                                    modifier = Modifier.scale(1f),
                                )
                            }
                        },
                        dismissContent = {
                            WordRow(word)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyRow() {
    Card(backgroundColor = MaterialTheme.colors.background) {
        Text(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            text = "Vocabulary List is empty."
        )
    }

}

@Composable
fun WordRow(
    word: WordEntry,
    modifier: Modifier = Modifier,
    onRowClick: (WordEntry) -> Unit = {},
) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(3.dp))
            .fillMaxWidth(), color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier
            .clickable {
                onRowClick(word)
            }
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                text = word.word,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = word.meaning,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = word.usage,
                style = MaterialTheme.typography.subtitle1
            )
        }

    }

}

@Composable
fun addInputForm(onAddWord: (WordEntry) -> Unit) {
    var word by remember {
        mutableStateOf("")
    }

    var meaning by remember {
        mutableStateOf("")
    }

    var usage by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    TextInputComponent(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth(),
        text = word,
        hintText = "Enter Word",
        onTextChange = {
            if (it.all { char ->
                    !char.isDigit() || char.isWhitespace()
                }) {
                Log.d(TAG, "RECEIVED = $it")
                word = it.capitalize(Locale.ROOT)
            }
        }
    )
    TextInputComponent(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .fillMaxWidth(),
        text = meaning,
        hintText = "Enter Meaning",
        onTextChange = { text ->
            if (text.all {
                    !it.isDigit()
                }) {
                meaning = text.capitalize()
                Log.d(TAG, "RECEIVED = $text")
            }
        }
    )
    TextInputComponent(
        text = usage,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 8.dp
            ),
        maxLine = 5,
        hintText = "Example Usage",
        onTextChange = { text ->
            if (text.all { !it.isDigit() }) {
                usage = text.capitalize()
                Log.d(TAG, "RECEIVED = $text")
            }
        }
    )
    CommonButton(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                bottom = 16.dp,
                end = 16.dp
            )
            .width(100.dp)
            .height(40.dp),
        text = "Save",
        onClick = {
            if (word.isNotEmpty()
                && meaning.isNotEmpty()
                && usage.isNotEmpty()
            ) {
                onAddWord(
                    WordEntry(
                        word = word,
                        meaning = meaning,
                        usage = usage
                    )
                )
                Toast.makeText(
                    context, "Entry Saved!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context, "Please enter all the mandatory fields!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
}

@Composable
fun addTopBar() {
    // add top action bar.
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            Icon(
                imageVector = Icons.Rounded.AddCircle, contentDescription = "Icon"
            )
        },
        backgroundColor = Color(0xFFFFA000)
    )
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(WordListDataSource().loadData(), {}, {})
}

@Preview(showBackground = true)
@Composable
fun NoteScreenEmptyPreview() {
    NoteScreen(wordsList = emptyList(), onAddWord = {}, onRemoveWord = {})
}