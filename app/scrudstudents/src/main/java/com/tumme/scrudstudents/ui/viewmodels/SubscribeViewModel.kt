package com.tumme.scrudstudents.ui.viewmodels

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.CourseEntity
import com.tumme.scrudstudents.data.local.model.SubscribeEntity
import com.tumme.scrudstudents.data.local.model.SubscriptionWithDetails
import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.repository.CourseRepository
import com.tumme.scrudstudents.data.repository.StudentRepository
import com.tumme.scrudstudents.data.repository.SubscriptionRepository
import com.tumme.scrudstudents.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscribeViewModel @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository,
    private val studentRepository: StudentRepository,
    private val courseRepository: CourseRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    // Flow for the list of subscriptions with student and course names.
    val subscriptions: StateFlow<List<SubscriptionWithDetails>> =
        subscriptionRepository.getAllSubscriptionsWithDetails()
            .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    // Flow for the list of all students.
    val students: StateFlow<List<UserEntity>> =
        studentRepository.getAllStudents()
            .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    // Flow for the list of all courses.
    val courses: StateFlow<List<CourseEntity>> =
        courseRepository.getAllCourses()
            .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    val studentSubscriptions: StateFlow<List<SubscriptionWithDetails>> =
        subscriptionRepository.getSubscribesByStudentWithDetails(sessionManager.getUserId())
            .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())
    fun addSubscription(subscription: SubscribeEntity) = viewModelScope.launch {
        subscriptionRepository.insertSubscribe(subscription)
    }

    fun deleteSubscription(subscription: SubscribeEntity) = viewModelScope.launch {
        subscriptionRepository.deleteSubscribe(subscription)
    }
    fun getSubscriptionsByStudent(sId: Int): StateFlow<List<SubscribeEntity>> {
        return subscriptionRepository.getSubscribesByStudent(sId)
            .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())
    }
}