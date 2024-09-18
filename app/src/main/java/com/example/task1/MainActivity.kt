package com.example.task1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Main()
        }
    }
}
sealed class UserRoutes(val route: String) {
    object Users : UserRoutes("users")
    object User : UserRoutes("user")
}
@Composable
fun Main() {
    val navController = rememberNavController()
    val employees = listOf(
        Employee(1, "Tom", 39),
        Employee(2, "Bob", 43),
        Employee(3, "Sam", 28)
    )
    NavHost(navController, startDestination = UserRoutes.Users.route) {
        composable(UserRoutes.Users.route) { Users(employees, navController) }
        composable(UserRoutes.User.route + "/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType }))
        {
                stackEntry ->
            val userId = stackEntry.arguments?.getInt("userId")
            User(userId, employees)
        }
    }
}
@Composable
fun Users(data: List<Employee>, navController: NavController){
    LazyColumn {
        items(data){
                u->
            Row(Modifier.fillMaxWidth()){
                Text(u.name,
                    Modifier.padding(8.dp).clickable {
                        navController.navigate("user/${u.id}") },
                    fontSize = 28.sp)
            }
        }
    }
}
@Composable
fun User(userId:Int?, data: List<Employee>){
    val user = data.find { it.id==userId }
    if(user!=null) {
        Column {
            Text("Id: ${user.id}", Modifier.padding(8.dp), fontSize = 22.sp)
            Text("Name: ${user.name}", Modifier.padding(8.dp), fontSize = 22.sp)
            Text("Age: ${user.age}", Modifier.padding(8.dp), fontSize = 22.sp)
        }
    }
    else{
        Text("User Not Found")
    }
}
data class Employee(val id:Int, val name:String, val age:Int)
