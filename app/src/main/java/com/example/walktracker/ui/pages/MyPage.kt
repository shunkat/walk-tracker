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
import com.example.walktracker.BuildConfig
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val _name = mutableStateOf("")
    val name: State<String> = _name


    private val _contact = mutableStateOf("")
    val contact: State<String> = _contact

    fun loadUserData(context: Context) {
        viewModelScope.launch {
            val sharedPref = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
            _name.value = sharedPref.getString("name", "") ?: ""
            _contact.value = sharedPref.getString("contact", "") ?: ""
        }
    }
}

@Composable
fun MyPage(navController: NavController, viewModel: MyPageViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val context = LocalContext.current
    val ownerMailAddress = BuildConfig.ownerMailAddress
    val ownerPhoneNumber = BuildConfig.ownerPhoneNumber

    LaunchedEffect(Unit) {
        viewModel.loadUserData(context)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ようこそ、${viewModel.name.value}さん！", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("何か問題があれば以下にご連絡ください。 電話番号:$ownerPhoneNumber メールアドレス:$ownerMailAddress")
    }
}