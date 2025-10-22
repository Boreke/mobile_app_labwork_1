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
import com.tumme.scrudstudents.ui.viewmodels.SignUpViewModel
import com.tumme.scrudstudents.data.local.model.UserEntity
import com.tumme.scrudstudents.data.local.model.Gender
import com.tumme.scrudstudents.data.local.model.Role
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onNavigateToLoginScreen: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {}
){
    // Observe one-shot events from ViewModel
    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            if (event == "Sign Up Success") {
                onSignUpSuccess()
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

            Text(text = "Sign up")

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text(text = "First name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(top = 8.dp)
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text(text = "Last name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = {
                    val user = UserEntity(
                        idUser = 0,
                        lastName = lastName,
                        firstName = firstName,
                        dateOfBirth = Date(),
                        gender = Gender.NotConcerned,
                        role = Role.Student
                    )
                    viewModel.signUp(user)
                },
                enabled = firstName.isNotBlank() && lastName.isNotBlank(),
                modifier = Modifier.padding(top = 12.dp)
            ) {
                Text(text = "Sign up")
            }

            TextButton(onClick = onNavigateToLoginScreen, modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "Already have an account? Log in")
            }
        }
    }
}