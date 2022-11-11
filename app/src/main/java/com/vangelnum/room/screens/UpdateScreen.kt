package com.vangelnum.room.screens

import android.util.Log
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
fun UpdateScreen(
    navController: NavController,
    mv: TodoViewModel,
    id: Long?,
    title: String?,
    subtitle: String?,
    color: String?,
) {

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    val currentDate = sdf.format(Date())

    var bordercolor1 by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Transparent))
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
    when(color) {
        "#FFFFFF" -> {
            bordercolor1 = BorderStroke(2.dp, Color.Black)
        }
        "#FF17BFC5" -> {
            bordercolor2 = BorderStroke(2.dp, Color.Black)
        }
        "#FF4CAF50" -> {
            bordercolor3 = BorderStroke(2.dp, Color.Black)
        }
        "#FFFF0000" -> {
            bordercolor4 = BorderStroke(2.dp, Color.Black)
        }
        "#FFFFFB00" -> {
            bordercolor5 = BorderStroke(2.dp, Color.Black)
        }
    }

    var blackcolor by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Black))
    }
    var whitecolor by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Transparent))
    }
    var newcolor by rememberSaveable {
        mutableStateOf(color)
    }

    val newtitle = rememberSaveable {
        mutableStateOf(title)
    }
    val newsubtitle = rememberSaveable {
        mutableStateOf(subtitle)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (!newtitle.value?.isEmpty()!! and !newsubtitle.value?.isEmpty()!!) {
                    mv.updateTodo(TodoItem(itemId = id!!,
                        title = newtitle.value!!,
                        newsubtitle.value!!,
                        currentDate,
                        color = newcolor!!))
                    navController.navigate(Screens.MainScreen.route)
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
                        newcolor = "#FFFFFFFF"
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
                        newcolor = "#FF17BFC5"
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
                        newcolor = "#FF4CAF50"
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
                        newcolor = "#FFFF0000"
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
                        newcolor = "#FFFFFB00"
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

            TextField(value = newtitle.value!!, onValueChange = {
                newtitle.value = it
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

            TextField(value = newsubtitle.value!!, onValueChange = {
                newsubtitle.value = it
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
