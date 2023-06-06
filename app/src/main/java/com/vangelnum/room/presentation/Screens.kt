package com.vangelnum.room.presentation

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object AddScreen : Screens("detail_screen")
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
