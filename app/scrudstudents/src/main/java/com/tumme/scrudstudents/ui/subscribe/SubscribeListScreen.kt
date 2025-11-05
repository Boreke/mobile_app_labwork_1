package com.tumme.scrudstudents.ui.subscribe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.Role
import com.tumme.scrudstudents.data.session.SessionManager
import com.tumme.scrudstudents.ui.components.TableHeader
import com.tumme.scrudstudents.ui.viewmodels.SubscribeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribeListScreen(
    viewModel: SubscribeViewModel = hiltViewModel(),
    onNavigateToForm: () -> Unit,
    sessionManager: SessionManager
) {
    val subscriptions by viewModel.subscriptions.collectAsState()
    val studentSubscriptions by viewModel.studentSubscriptions.collectAsState()
    var currentUserRole = sessionManager.getUserRole()
    Scaffold(
        topBar = { TopAppBar(title = { Text("Subscriptions") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToForm) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TableHeader(
                cells = listOf("Student", "Course", "Score"),
                weights = listOf(0.4f, 0.4f, 0.2f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (currentUserRole != Role.Student) {
                    items(studentSubscriptions) { studentSubscription ->
                        SubscriptionRow(studentSubscription)
                    }
                } else {
                    items(subscriptions) { subscription ->
                        SubscriptionRow(subscription)
                    }
                }
            }
        }
    }
}
