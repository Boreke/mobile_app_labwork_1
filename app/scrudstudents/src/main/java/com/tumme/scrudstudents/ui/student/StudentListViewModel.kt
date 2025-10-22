package com.tumme.scrudstudents.ui.student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.data.repository.SCRUDRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for the Student List Screen, handles business logic.
@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val repo: SCRUDRepository // Repository for data operations.
) : ViewModel() {

    // Holds the list of students as a StateFlow, which the UI observes.
    val students: StateFlow<List<StudentEntity>> = 
        repo.getAllStudents().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Used to send one-time events to the UI (e.g., for Snackbars).
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()

    // Deletes a student and emits an event.
    fun deleteStudent(student: StudentEntity) = viewModelScope.launch {
        repo.deleteStudent(student)
        _events.emit("Student deleted")
    }

    // Inserts a student and emits an event.
    fun insertStudent(student: StudentEntity) = viewModelScope.launch {
        repo.insertStudent(student)
        _events.emit("Student inserted")
    }

    // Finds a student by their ID.
    suspend fun findStudent(id: Int) = repo.getStudentById(id)

}
