package com.tumme.scrudstudents.ui.course

import com.tumme.scrudstudents.data.local.model.CourseEntity

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CourseRow(
    course: CourseEntity,
    onEdit: ()->Unit,
    onDelete: ()->Unit,
    onView: ()->Unit,
    onShare: ()->Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = course.nameCourse, modifier = Modifier.weight(0.25f))
        Text(text = "${course.ectsCourse}", modifier = Modifier.weight(0.25f))
        Text(text = course.levelCourse.value, modifier = Modifier.weight(0.25f))

        Row(modifier = Modifier.weight(0.25f)) {
            IconButton(onClick = onEdit) { Icon(Icons.Default.Edit, contentDescription="Edit") }
            IconButton(onClick = onDelete) { Icon(Icons.Default.Delete, contentDescription="Delete") }
            IconButton(onClick = onShare) { Icon(Icons.Default.Share, contentDescription="Share") }
            IconButton(onClick = onView) { Icon(Icons.Default.Info, contentDescription="View") }
        }
    }
    HorizontalDivider()
}
