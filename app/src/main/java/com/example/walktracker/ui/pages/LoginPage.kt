package com.example.walktracker.ui.pages

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _contact = mutableStateOf("")
    val contact: State<String> = _contact

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun onContactChange(newContact: String) {
        _contact.value = newContact
    }

    fun login(context: Context, navController: NavController) {
        viewModelScope.launch {
            val sharedPref = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("name", name.value)
                putString("contact", contact.value)
                apply()
            }
            navController.navigate("my_page") {
                popUpTo("login") { inclusive = true }
            }
        }
    }
}

@Composable
fun LoginPage(navController: NavController, viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.name.value,
            onValueChange = viewModel::onNameChange,
            label = { Text("名前") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = viewModel.contact.value,
            onValueChange = viewModel::onContactChange,
            label = { Text("連絡先（任意）") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { viewModel.login(context, navController) },
            enabled = viewModel.name.value.isNotBlank()
        ) {
            Text("ログイン")
        }
    }
}