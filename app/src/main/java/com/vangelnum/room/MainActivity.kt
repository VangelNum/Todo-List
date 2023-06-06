package com.vangelnum.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vangelnum.room.presentation.Screens
import com.vangelnum.room.presentation.TodoViewModel
import com.vangelnum.room.presentation.screens.AddScreen
import com.vangelnum.room.presentation.screens.MainScreen
import com.vangelnum.room.presentation.screens.UpdateScreen
import com.vangelnum.room.ui.theme.RoomTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomTheme {
                val todoViewModel: TodoViewModel by viewModels()
                val todoItems = todoViewModel.allNotes.observeAsState(emptyList()).value
                val navController = rememberNavController()
                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(stringResource(id = R.string.my_notes))
                    }, navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                        }
                    })
                }) {
                    NavHost(
                        navController = navController,
                        modifier = Modifier.padding(it),
                        startDestination = Screens.MainScreen.route
                    ) {
                        composable(
                            route = Screens.MainScreen.route,
                        ) {
                            MainScreen(
                                todoItems = todoItems,
                                navigateToUpdateScreen = { id, title, subtitle, color ->
                                    navController.navigate(
                                        Screens.UpdateScreen.route + "/$id/$title/$subtitle/$subtitle"
                                    )
                                },
                                navigateToAddScreen = {
                                    navController.navigate(Screens.AddScreen.route)
                                },
                                deleteTodo = { todoItem ->
                                    todoViewModel.deleteTodo(todoItem)
                                }

                            )

                        }
                        composable(
                            route = Screens.AddScreen.route
                        ) {
                            AddScreen(addNote = {
                                todoViewModel.addTodo(it)
                            }, navigateToMainScreen = {
                                navController.navigate(Screens.MainScreen.route)
                            })
                        }
                        composable(
                            route = Screens.UpdateScreen.route + "/{id}/{title}/{subtitle}/{color}",
                            arguments =
                            listOf(
                                navArgument("id") {
                                    type = NavType.IntType
                                    defaultValue = 0
                                    nullable = false
                                },
                                navArgument("title") {
                                    type = NavType.StringType
                                    defaultValue = "null"
                                    nullable = false
                                },
                                navArgument("subtitle") {
                                    type = NavType.StringType
                                    defaultValue = "null"
                                    nullable = false
                                },
                                navArgument("color") {
                                    type = NavType.StringType
                                    defaultValue = "FFFFFFFF"
                                    nullable = false
                                },
                            )
                        ) { entry ->
                            val id = entry.arguments?.getInt("id")
                            val title = entry.arguments?.getString("title")
                            val subtitle = entry.arguments?.getString("subtitle")
                            val color = entry.arguments?.getString("color")
                            UpdateScreen(
                                id = id,
                                title = title,
                                subtitle = subtitle,
                                color = color,
                                updateTodo = { todoItem ->
                                    todoViewModel.updateTodo(todoItem)
                                },
                                navigateToMainScreen = {
                                    navController.navigate(Screens.MainScreen.route)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
