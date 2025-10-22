package com.tumme.scrudstudents.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tumme.scrudstudents.ui.viewmodels.LoginViewModel
import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.local.model.Gender
import com.tumme.scrudstudents.data.local.model.Role
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToSignUpScreen: () -> Unit = {},
    onLoginSuccess: () -> Unit = {}
){
    // Collect one-shot events from the ViewModel and react to login success.
    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            if (event == "Logged in") {
                onLoginSuccess()
            }
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }

            Text(text = "Login", style = androidx.compose.material3.MaterialTheme.typography.headlineMedium)

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(text = "First name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Last name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Button(onClick = {
                // Build a minimal UserEntity using the provided first/last name.
                // We provide placeholder/default values for the other required fields.
                val user = UserEntity(
                    idUser = 0,
                    lastName = lastName,
                    firstName = firstName,
                    dateOfBirth = Date(),
                    gender = Gender.NotConcerned,
                    role = Role.Student
                )
                viewModel.login(user)
            }) {
                Text(text = "Login")
            }

            TextButton(onClick = onNavigateToSignUpScreen) {
                Text(text = "Don't have an account? Sign up")
            }
        }
    }
}
