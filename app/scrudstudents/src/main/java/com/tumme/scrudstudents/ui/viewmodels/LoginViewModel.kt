package com.tumme.scrudstudents.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.repository.AuthRepository
import com.tumme.scrudstudents.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager // Hilt injects this for us.
): ViewModel() {
    private val _events= MutableSharedFlow<String>()
    val events= _events.asSharedFlow()

    fun login(user: UserEntity){
        viewModelScope.launch {
            val currentUser = authRepository.login(user)
            if (currentUser != null) {
                // On successful login, save the user's ID to the session.
                sessionManager.saveSession(currentUser.idUser, currentUser.role)
                _events.emit("Logged in")
            } else {
                _events.emit("Login failed")
            }
        }
    }
}