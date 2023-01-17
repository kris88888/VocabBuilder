package com.vocab.builder.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current

    Button(onClick = {
        localSoftwareKeyboardController?.hide()
        onClick()
                     },
    enabled = enabled,
        shape = RoundedCornerShape(5.dp),
        modifier = modifier,
        ) {
        Text(text = text, fontSize = 14.sp)
    }
}