package com.ishak.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ishak.myapplication.ui.theme.CryptoMoneyTheme
import com.ishak.myapplication.view.CryptoDetailScreen
import com.ishak.myapplication.view.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("RememberReturnType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoMoneyTheme {
                val navController= rememberNavController()

                NavHost(navController = navController, startDestination = "crypto_list_screen" ){

                    composable(route="crypto_list_screen"){
                        CryptoListScreen(navController)
                    }

                    composable(route="crypto_detail_screen/{id}", arguments = listOf(
                        navArgument("id"){
                            type= NavType.StringType
                        }
                    )){
                        val id= remember {
                            it.arguments?.getString("id")
                        }

                        CryptoDetailScreen(id = id?:"1", navController =navController )

                    }



                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Text(text = "dsf")
}

