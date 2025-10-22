package com.tumme.scrudstudents.ui.student

import com.tumme.scrudstudents.ui.components.TableHeader
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

// Composable screen that displays a list of students.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
    viewModel: StudentListViewModel = hiltViewModel(), // Injected ViewModel.
    onNavigateToForm: () -> Unit = {}, // Navigation callback to the form screen.
    onNavigateToDetail: (Int) -> Unit = {} // Navigation callback to the detail screen.
) {
    // `collectAsState` observes the `students` Flow from the ViewModel.
    // Recomposition happens automatically when the student list changes.
    val students by viewModel.students.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Students") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToForm) { Text("+") }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Display the header for the list.
            TableHeader(
                cells = listOf("DOB", "Last", "First", "Gender", "Actions"),
                weights = listOf(0.25f, 0.25f, 0.25f, 0.15f, 0.10f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display the list of students efficiently.
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(students) { student ->
                    StudentRow(
                        student = student,
                        onEdit = { /* Not yet implemented */ },
                        onDelete = { viewModel.deleteStudent(student) }, // Deletes the student.
                        onView = { onNavigateToDetail(student.idStudent) }, // Navigates to student details.
                        onShare = { /* Not yet implemented */ }
                    )
                }
            }
        }
    }
}
