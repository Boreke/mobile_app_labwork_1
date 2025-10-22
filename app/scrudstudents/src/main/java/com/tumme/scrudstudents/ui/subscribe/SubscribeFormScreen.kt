package com.tumme.scrudstudents.ui.subscribe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.ui.viewmodels.SubscribeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribeFormScreen(
    viewModel: SubscribeViewModel = hiltViewModel(),
    onSaved: () -> Unit
) {
    val students by viewModel.students.collectAsState()
    val courses by viewModel.courses.collectAsState()

    var selectedStudent by remember { mutableStateOf<UserEntity?>(null) }
    var selectedCourse by remember { mutableStateOf<CourseEntity?>(null) }
    var score by remember { mutableStateOf("") }

    var studentExpanded by remember { mutableStateOf(false) }
    var courseExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Student Dropdown
        ExposedDropdownMenuBox(
            expanded = studentExpanded,
            onExpandedChange = { studentExpanded = !studentExpanded }
        ) {
            TextField(
                value = selectedStudent?.let { "${it.firstName} ${it.lastName}" } ?: "Select Student",
                onValueChange = {},
                readOnly = true,
                label = { Text("Student") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = studentExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = studentExpanded,
                onDismissRequest = { studentExpanded = false }
            ) {
                students.forEach { student ->
                    DropdownMenuItem(
                        text = { Text("${student.firstName} ${student.lastName}") },
                        onClick = { 
                            selectedStudent = student
                            studentExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Course Dropdown
        ExposedDropdownMenuBox(
            expanded = courseExpanded,
            onExpandedChange = { courseExpanded = !courseExpanded }
        ) {
            TextField(
                value = selectedCourse?.nameCourse ?: "Select Course",
                onValueChange = {},
                readOnly = true,
                label = { Text("Course") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = courseExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = courseExpanded,
                onDismissRequest = { courseExpanded = false }
            ) {
                courses.forEach { course ->
                    DropdownMenuItem(
                        text = { Text(course.nameCourse) },
                        onClick = { 
                            selectedCourse = course
                            courseExpanded = false 
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Score Input
        TextField(
            value = score,
            onValueChange = { score = it },
            label = { Text("Score") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = {
                val student = selectedStudent
                val course = selectedCourse
                if (student != null && course != null && score.isNotEmpty()) {
                    val subscription = SubscribeEntity(
                        idUser = student.idUser,
                        idCourse = course.idCourse,
                        score = score.toFloatOrNull() ?: 0f
                    )
                    viewModel.addSubscription(subscription)
                    onSaved()
                }
            },
            enabled = selectedStudent != null && selectedCourse != null && score.isNotEmpty()
        ) {
            Text("Save")
        }
    }
}
