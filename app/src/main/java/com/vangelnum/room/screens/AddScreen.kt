package com.vangelnum.room.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vangelnum.room.R
import com.vangelnum.room.TodoItem
import com.vangelnum.room.TodoViewModel
import com.vangelnum.room.navigation.Screens
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddScreen(navController: NavController, mv: TodoViewModel) {
    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())


    var color by rememberSaveable {
        mutableStateOf("#FFFFFF")
    }

    val title = rememberSaveable {
        mutableStateOf("")
    }
    val subtitle = rememberSaveable {
        mutableStateOf("")
    }

    var bordercolor1 by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Black))
    }
    var bordercolor2 by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Transparent))
    }
    var bordercolor3 by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Transparent))
    }
    var bordercolor4 by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Transparent))
    }
    var bordercolor5 by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Transparent))
    }
    var blackcolor by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Black))
    }
    var whitecolor by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Transparent))
    }

    val context: Context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (!title.value.isEmpty() and !subtitle.value.isEmpty()) {
                    mv.addTodo(TodoItem(
                        itemId = 0,
                        title = title.value,
                        subtitle = subtitle.value,
                        time = currentDate,
                        color = color)
                    )
                    navController.navigate(Screens.MainScreen.route)
                } else {
                    Toast.makeText(context,"Поля не могут быть пустыми",Toast.LENGTH_LONG).show()
                }
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Card(modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clickable {
                        color = "#FFFFFFFF"
                        bordercolor1 = blackcolor
                        bordercolor2 = whitecolor
                        bordercolor3 = whitecolor
                        bordercolor4 = whitecolor
                        bordercolor5 = whitecolor
                    },
                    backgroundColor = Color(0xFFFFFFFF),
                    shape = CircleShape,
                    elevation = 5.dp,
                    border = bordercolor1
                ) {

                }
                Card(modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clickable {
                        color = "#FF17BFC5"
                        bordercolor1 = whitecolor
                        bordercolor2 = blackcolor
                        bordercolor3 = whitecolor
                        bordercolor4 = whitecolor
                        bordercolor5 = whitecolor
                    },
                    backgroundColor = Color(0xFF17BFC5),
                    shape = CircleShape,
                    elevation = 5.dp,
                    border = bordercolor2
                ) {

                }
                Card(modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clickable {
                        color = "#FF4CAF50"
                        bordercolor1 = whitecolor
                        bordercolor2 = whitecolor
                        bordercolor3 = blackcolor
                        bordercolor4 = whitecolor
                        bordercolor5 = whitecolor
                    },
                    backgroundColor = Color(0xFF4CAF50),
                    shape = CircleShape,
                    elevation = 5.dp,
                    border = bordercolor3
                ) {

                }
                Card(modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clickable {
                        color = "#FFFF0000"
                        bordercolor1 = whitecolor
                        bordercolor2 = whitecolor
                        bordercolor3 = whitecolor
                        bordercolor4 = blackcolor
                        bordercolor5 = whitecolor
                    },
                    backgroundColor = Color(0xFFFF0000),
                    shape = CircleShape,
                    elevation = 5.dp,
                    border = bordercolor4
                ) {

                }
                Card(modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clickable {
                        color = "#FFFFFB00"
                        bordercolor1 = whitecolor
                        bordercolor2 = whitecolor
                        bordercolor3 = whitecolor
                        bordercolor4 = whitecolor
                        bordercolor5 = blackcolor
                    },
                    backgroundColor = Color(0xFFFFFB00),
                    shape = CircleShape,
                    elevation = 5.dp,
                    border = bordercolor5
                ) {

                }
            }
            Spacer(modifier = Modifier.height(10.dp))

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
