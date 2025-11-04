package com.tumme.scrudstudents.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioButton(
    options: List<String> = listOf("Option 1", "Option 2", "Option 3"),
    onSelected: (String) -> Unit = {}
) {
    var selected by rememberSaveable { mutableStateOf(options.first()) }

    Row(
        modifier = Modifier.selectableGroup().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .selectable(
                        selected = (option == selected),
                        onClick = {
                            selected = option
                            onSelected(option)
                        }
                    )
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (option == selected),
                    onClick = {
                        selected = option
                        onSelected(option)
                    }
                )
                Text(text = option, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}