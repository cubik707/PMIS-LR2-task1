package com.example.task1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}
@Composable
fun Main() {
    val navController = rememberNavController()
    Column(Modifier.padding(8.dp)) {
        NavBar(navController = navController)
        NavHost(navController, startDestination = NavRoutes.Home.route) {
            composable(NavRoutes.Home.route) { Home() }
            composable(NavRoutes.Contacts.route) { Contacts() }
            composable(NavRoutes.About.route) { About() }
        }
    }
}
@Composable
fun NavBar(navController: NavController){
    Row(
        Modifier.fillMaxWidth().padding(bottom = 8.dp)){
        Text("Home",
            Modifier
                .weight(0.33f)
                .clickable { navController.navigate(NavRoutes.Home.route) },
            fontSize = 22.sp, color= Color(0xFF6650a4))
        Text("Contacts",
            Modifier
                .weight(0.33f)
                .clickable { navController.navigate(NavRoutes.Contacts.route) },
            fontSize = 22.sp, color= Color(0xFF6650a4))
        Text("About",
            Modifier
                .weight(0.33f)
                .clickable { navController.navigate(NavRoutes.About.route) },
            fontSize = 22.sp, color= Color(0xFF6650a4))
    }
}
@Composable
fun Home(){
    Text("Home Page", fontSize = 30.sp)
}
@Composable
fun Contacts(){
    Text("Contact Page", fontSize = 30.sp)
}
@Composable
fun About(){
    Text("About Page", fontSize = 30.sp)
}
sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Contacts : NavRoutes("contact")
    object About : NavRoutes("about")
}
