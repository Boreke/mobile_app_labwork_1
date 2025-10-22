package com.tumme.scrudstudents.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for the Student List Screen, handles business logic.
@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val repo: StudentRepository // Repository for data operations.
) : ViewModel() {

    // Holds the list of students as a StateFlow, which the UI observes.
    val students: StateFlow<List<UserEntity>> =
        repo.getAllStudents().stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    // Used to send one-time events to the UI (e.g., for Snackbars).
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    // Deletes a student and emits an event.
    fun deleteStudent(student: UserEntity) = viewModelScope.launch {
        repo.deleteStudent(student)
        _events.emit("Student deleted")
    }

    // Inserts a student and emits an event.
    fun insertStudent(student: UserEntity) = viewModelScope.launch {
        repo.insertStudent(student)
        _events.emit("Student inserted")
    }

    // Finds a student by their ID.
    suspend fun findStudent(id: Int) = repo.getStudentById(id)

}