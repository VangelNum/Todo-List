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
import com.vangelnum.room.TodoViewModel
import com.vangelnum.room.navigation.Screens
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun UpdateScreen(
    navController: NavController,
    mv: TodoViewModel,
    id: Long?,
    title: String?,
    subtitle: String?,
) {

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        Text(text = "Update TODO", fontSize = 30.sp)
        val newtitle = rememberSaveable {
            mutableStateOf(title)
        }
        val newsubtitle = rememberSaveable {
            mutableStateOf(subtitle)
        }

        TextField(value = newtitle.value!!, onValueChange = {
            newtitle.value = it
        })

        TextField(value = newsubtitle.value!!, onValueChange = {
            newsubtitle.value = it
        })
        Button(onClick = {
            //mv.updateTodo(TodoItem(itemId = id!!,title = newtitle.value!!,newsubtitle.value!!,currentDate))
            navController.navigate(Screens.MainScreen.route)
        }, modifier = Modifier.padding(top = 50.dp)) {
            Text(text = "save update and go back")
        }
    }
}
