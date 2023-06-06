package com.vangelnum.room.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vangelnum.room.R
import com.vangelnum.room.data.model.TodoItem
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UpdateScreen(
    id: Int?,
    title: String?,
    subtitle: String?,
    color: String?,
    updateTodo: (todoItem: TodoItem) -> Unit,
    navigateToMainScreen: () -> Unit
) {

    val sdf: DateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US)
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
    when (color) {
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

    val blackcolor by remember {
        mutableStateOf(BorderStroke(2.dp, Color.Black))
    }
    val whitecolor by remember {
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Card(
                modifier = Modifier
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
            Card(
                modifier = Modifier
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
            Card(
                modifier = Modifier
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
            Card(
                modifier = Modifier
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
            Card(
                modifier = Modifier
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

        TextField(
            value = newtitle.value!!, onValueChange = {
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

        TextField(
            value = newsubtitle.value!!,
            onValueChange = {
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        IconButton(onClick = {
                updateTodo(
                    TodoItem(itemId = id?: 0,
                        title = newtitle.value?: "empty title",
                        newsubtitle.value?: "empty subtitle",
                        currentDate,
                        color = newcolor?: "#FF17BFC5")
                )
                navigateToMainScreen()

        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_save_24),
                contentDescription = stringResource(
                    id = R.string.save_note
                )
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewUpdateScreen() {
    UpdateScreen(id = 0, title = "title", subtitle = "subtitle", color = "#FF17BFC5", updateTodo = {

    }) {

    }
}
