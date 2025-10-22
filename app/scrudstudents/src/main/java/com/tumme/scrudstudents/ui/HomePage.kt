package com.tumme.scrudstudents.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageScreen(
    onNavigateToCourses: () -> Unit = {}, 
    onNavigateToStudents: () -> Unit = {},
    onNavigateToSubscriptions: () -> Unit = {}
){
    Scaffold(
        topBar = { TopAppBar(title = { Text("Home") }) }
    ){ contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onNavigateToCourses) {
                Text("Manage Courses")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToStudents) {
                Text("Manage Students")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToSubscriptions) {
                Text("Manage Subscriptions")
            }
        }
    }
}
