package com.vangelnum.room.navigation

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vangelnum.room.TodoViewModel
import com.vangelnum.room.screens.DetailScreen
import com.vangelnum.room.screens.MainScreen
import com.vangelnum.room.screens.UpdateScreen

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object DetailScreen : Screens("detail_screen")
    object UpdateScreen : Screens("update_screen")

    fun withArgs(vararg args: String?): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}

@Composable
fun NavGraph(viewmodel: TodoViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.MainScreen.route) {
        composable(
            route = Screens.MainScreen.route,
        ) {
            MaterialTheme(
                colors = darkColors(background = Color.Black)
            ) {
                MainScreen(
                    viewmodel = viewmodel,
                    navController = navController,
                )
            }
        }
        composable(
            route = Screens.DetailScreen.route
        ) { entry ->
            DetailScreen(navController, viewmodel)
        }
        composable(
            route = Screens.UpdateScreen.route + "/{id}/{title}/{subtitle}",
            arguments =
            listOf(
                navArgument("id") {
                    type = NavType.LongType
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
                }
            )
        ) { entry ->
            val id = entry.arguments?.getLong("id")
            val title = entry.arguments?.getString("title")
            val subtitle = entry.arguments?.getString("subtitle")
            UpdateScreen(navController = navController,
                mv = viewmodel,
                id = id,
                title = title,
                subtitle = subtitle)
        }
    }
}