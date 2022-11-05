package com.vangelnum.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vangelnum.room.ui.theme.RoomTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm by viewModels<TodoViewModel>()
            Navigation(viewmodel = vm)
        }
    }
}

@Composable
fun MainScreen(viewmodel: TodoViewModel, navController: NavController) {
    val items = viewmodel.readAllData.observeAsState(listOf()).value
    RoomTheme {
        Column {
            val text = rememberSaveable {
                mutableStateOf("")
            }
            TextField(value = text.value, onValueChange = {
                text.value = it
            })
            Button(onClick = { navController.navigate(Screens.DetailScreen.route) }) {
                Text(text = "this is next page")
            }
            Button(onClick = {
                viewmodel.addTodo(TodoItem(itemId = 0, itemName = text.value, isDone = true))
            }) {
                Text(text = "add with textfield")
            }
            LazyColumn {
                items(items) {
                    Row {
                        Button(onClick = {  }) {
                            Text(text = it.itemName)
                        }
                        Spacer(modifier = Modifier.width(50.dp))
                        Button(onClick = {
                            viewmodel.deleteTodo(it)
                        }) {
                            Text(text = "delete left todo")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(viewmodel: TodoViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.MainScreen.route) {
        composable(
            route = Screens.MainScreen.route,
//            arguments = listOf(
//                navArgument("title") {
//                    type = NavType.StringType
//                    defaultValue = "yep"
//                    nullable = true
//                }
//            )
        ) {
            MainScreen(
                viewmodel = viewmodel,
                navController = navController
            )
            //title = backStackEntry.arguments?.getString("title")

        }
        composable(
            route = Screens.DetailScreen.route
        ) {
            DetailScreen(navController, viewmodel)
        }
    }
}

@Composable
fun DetailScreen(navController: NavController, mv: TodoViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Text(text = "TITLES", fontSize = 30.sp)
        val title = rememberSaveable {
            mutableStateOf("")
        }
        val subtitle = rememberSaveable {
            mutableStateOf("")
        }
        TextField(value = title.value, onValueChange = {
            title.value = it
        })
        TextField(value = subtitle.value, onValueChange = {
            subtitle.value = it
        })
        Button(onClick = {
            mv.addTodo(TodoItem(itemId = 0, itemName = title.value, isDone = true))
            navController.navigate(Screens.MainScreen.route)
        }, modifier = Modifier.padding(top = 50.dp)) {

            Text(text = "save it and go back")
        }
    }
}