package com.tumme.scrudstudents.ui.course



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.LevelCourse
import com.tumme.scrudstudents.ui.viewmodels.CourseListViewModel

@Composable
fun CourseFormScreen(
    viewModel: CourseListViewModel = hiltViewModel(),
    onSaved: ()->Unit = {}
) {
    var id by remember { mutableStateOf((0..10000).random()) }
    var name by remember { mutableStateOf("") }
    var ects by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(value = name, onValueChange = { name = it }, label = { Text("Course Name") })
        Spacer(Modifier.height(8.dp))
        TextField(value = ects, onValueChange = { ects = it }, label = { Text("Course ects") })
        Spacer(Modifier.height(8.dp))
        TextField(value = level, onValueChange = { level = it }, label = { Text("Course Level") })
        Spacer(Modifier.height(8.dp))

        // level selector simple
        Row {
            LevelCourse.entries.forEach { l->
                Button(onClick = { level = l.value }, modifier = Modifier.padding(end = 8.dp)) {
                    Text(l.value)
                }
            }
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val course = CourseEntity(
                idCourse = id,
                nameCourse = name,
                ectsCourse = ects.toFloat(),
                levelCourse = LevelCourse.valueOf(level)
            )
            viewModel.insertCourse(course)
            onSaved()
        }) {
            Text("Save")
        }
    }
}
