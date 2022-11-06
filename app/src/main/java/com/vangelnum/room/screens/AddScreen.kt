package com.vangelnum.room.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vangelnum.room.TodoItem
import com.vangelnum.room.TodoViewModel
import com.vangelnum.room.navigation.Screens

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
            mv.addTodo(TodoItem(itemId = 0, title = title.value, isDone = true, subtitle = "cea"))
            navController.navigate(Screens.MainScreen.route)
        }, modifier = Modifier.padding(top = 50.dp)) {
            Text(text = "save it and go back")
        }
    }
}