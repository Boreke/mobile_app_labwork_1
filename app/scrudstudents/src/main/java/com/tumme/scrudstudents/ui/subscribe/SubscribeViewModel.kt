package com.tumme.scrudstudents.ui.subscribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.StudentEntity
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.local.model.SubscriptionWithDetails
import com.tumme.scrudstudents.data.repository.SCRUDRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscribeViewModel @Inject constructor(
    private val repository: SCRUDRepository
) : ViewModel() {

    // Flow for the list of subscriptions with student and course names.
    val subscriptions: StateFlow<List<SubscriptionWithDetails>> = 
        repository.getAllSubscriptionsWithDetails()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Flow for the list of all students.
    val students: StateFlow<List<StudentEntity>> = 
        repository.getAllStudents()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Flow for the list of all courses.
    val courses: StateFlow<List<CourseEntity>> = 
        repository.getAllCourses()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addSubscription(subscription: SubscribeEntity) = viewModelScope.launch {
        repository.insertSubscribe(subscription)
    }

    fun deleteSubscription(subscription: SubscribeEntity) = viewModelScope.launch {
        repository.deleteSubscribe(subscription)
    }
}
