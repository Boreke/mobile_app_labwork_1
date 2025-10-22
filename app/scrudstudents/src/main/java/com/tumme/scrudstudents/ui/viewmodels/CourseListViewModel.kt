package com.tumme.scrudstudents.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.repository.CourseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for the Course List Screen, handles business logic.
@HiltViewModel
class CourseListViewModel @Inject constructor(
    private val repo: CourseRepository // Repository for data operations.
) : ViewModel() {

    // Holds the list of courses as a StateFlow, which the UI observes.
    val courses: StateFlow<List<CourseEntity>> =
        repo.getAllCourses().stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    // Used to send one-time events to the UI (e.g., for Snackbars).
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    // Deletes a course and emits an event.
    fun deleteCourse(course: CourseEntity) = viewModelScope.launch {
        repo.deleteCourse(course)
        _events.emit("Course deleted")
    }

    // Inserts a course and emits an event.
    fun insertCourse(course: CourseEntity) = viewModelScope.launch {
        repo.insertCourse(course)
        _events.emit("Course inserted")
    }

    // Finds a course by their ID.
    suspend fun findCourse(id: Int) = repo.getCourseById(id)

}