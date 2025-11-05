package com.tumme.scrudstudents.ui.course


import com.tumme.scrudstudents.ui.components.TableHeader
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.session.SessionManager
import com.tumme.scrudstudents.ui.viewmodels.CourseListViewModel
import com.tumme.scrudstudents.data.local.model.Role // added import

// Composable screen that displays a list of courses.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListScreen(
    viewModel: CourseListViewModel = hiltViewModel(), // Injected ViewModel.
    onNavigateToForm: () -> Unit = {}, // Navigation callback to the form screen.
    onNavigateToDetail: (Int) -> Unit = {}, // Navigation callback to the detail screen.
    sessionManager: SessionManager
) {
    // `collectAsState` observes the `courses` Flow from the ViewModel.
    // Recomposition happens automatically when the course list changes.
    val courses by viewModel.courses.collectAsState()

    // Read the role once and reuse. This makes debugging easier and avoids multiple
    // calls to SharedPreferences during composition.
    val role = sessionManager.getUserRole()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Courses") }) },
        floatingActionButton = {
            // Explicit check: only Admins and Teachers get the FAB.
            if (role == Role.Admin || role == Role.Teacher) {
                FloatingActionButton(onClick = onNavigateToForm) { Text("+") }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Debug: show current role read from SessionManager (temporary)

            TableHeader(
                cells = listOf("Name", "ECTS", "Level", "Actions"),
                weights = listOf(0.25f, 0.25f, 0.25f, 0.25f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Display the list of courses efficiently.
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(courses) { course ->
                    CourseRow(
                        course = course,
                        onEdit = { /* Not yet implemented */ },
                        onDelete = { viewModel.deleteCourse(course) }, // Deletes the course.
                        onView = { onNavigateToDetail(course.idCourse) }, // Navigates to course details.
                        onSubscribe = { /* Not yet implemented */ }
                    )
                }
            }
        }
    }
}
