package com.vangelnum.room.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vangelnum.room.R
import com.vangelnum.room.TodoItem
import com.vangelnum.room.TodoViewModel
import com.vangelnum.room.navigation.Screens
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DetailScreen(navController: NavController, mv: TodoViewModel) {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())
    Log.d("check",Calendar.getInstance().time.toString())

    val title = rememberSaveable {
        mutableStateOf("")
    }
    val subtitle = rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (!title.value.isEmpty() and !subtitle.value.isEmpty()) {
                    mv.addTodo(TodoItem(
                                itemId = 0,
                                title = title.value,
                                subtitle = subtitle.value,
                                time = currentDate)
                    )
                }
                navController.navigate(Screens.MainScreen.route)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_save_24),
                    contentDescription = "save"
                )
            }
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

            TextField(value = title.value, onValueChange = {
                title.value = it
            },
                enabled = true,
                textStyle = TextStyle(Color.Black),
                placeholder = {
                    Text(
                        text = "Some Title",
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.Black,
                    leadingIconColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Black,
                    backgroundColor = Color.White
                )
            )

            TextField(value = subtitle.value, onValueChange = {
                subtitle.value = it
            },
                enabled = true,
                textStyle = TextStyle(Color.Black),
                placeholder = {
                    Text(
                        text = "Some Subtitle",
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.Black,
                    leadingIconColor = Color.Black,
                    trailingIconColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Black,
                    backgroundColor = Color.White
                )
            )
        }

    }
}