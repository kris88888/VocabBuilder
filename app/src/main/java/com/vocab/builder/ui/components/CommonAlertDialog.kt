package com.vocab.builder.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun CommonAlertDialog(
    confirmButtonText: String,
    dismissText: String = "Cancel",
    dismissAction: () -> Unit,
    confirmAction: () -> Unit
) {
        AlertDialog(onDismissRequest = { },
            confirmButton = {
                TextButton(onClick = {
                    confirmAction() }) {
                    Text(text = confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dismissAction()
                }) {
                    Text(text = dismissText)
                }
            },
            title = { Text(text = "Are you Sure?") },
            text = { Text(text = "Are you sure you want to delete?") }
        )
}