package com.tumme.scrudstudents.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tumme.scrudstudents.data.local.model.Role
import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _events= MutableSharedFlow<String>()
    val events= _events.asSharedFlow()

    fun signUp(user: UserEntity){
        viewModelScope.launch {
            authRepository.signUp(user)
            _events.emit("Sign Up Success")

        }
    }


}