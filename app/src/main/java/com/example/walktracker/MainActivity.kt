package com.example.walktracker

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.walktracker.ui.pages.LoginPage
import com.example.walktracker.ui.pages.MyPage
import com.example.walktracker.ui.theme.WalkTrackerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val startDestination by remember {
        mutableStateOf(
            if (isUserLoggedIn(context)) "my_page" else "login"
        )
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") { LoginPage(navController) }
        composable("my_page") { MyPage(navController) }
    }
}

fun isUserLoggedIn(context: Context): Boolean {
    val sharedPref = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    return sharedPref.getString("name", null) != null
}