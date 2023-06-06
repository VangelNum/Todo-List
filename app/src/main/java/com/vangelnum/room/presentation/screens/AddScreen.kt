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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
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
fun AddScreen(
    addNote: (note: TodoItem) -> Unit,
    navigateToMainScreen: () -> Unit
) {
    val sdf: DateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US)
    val currentDate = sdf.format(Date())

    var color by rememberSaveable {
        mutableStateOf("#FFFFFFFF")
    }

    val title = rememberSaveable {
        mutableStateOf("")
    }
    val subtitle = rememberSaveable {
        mutableStateOf("")
    }
    val textFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        cursorColor = Color.Black,
        leadingIconColor = Color.Black,
        trailingIconColor = Color.Black,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Black,
        backgroundColor = Color.White
    )

    val borderColor1 = remember { mutableStateOf(BorderStroke(2.dp, Color.Black)) }
    val borderColor2 = remember { mutableStateOf(BorderStroke(2.dp, Color.Transparent)) }
    val borderColor3 = remember { mutableStateOf(BorderStroke(2.dp, Color.Transparent)) }
    val borderColor4 = remember { mutableStateOf(BorderStroke(2.dp, Color.Transparent)) }
    val borderColor5 = remember { mutableStateOf(BorderStroke(2.dp, Color.Transparent)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            val colorItems = listOf(
                Pair("#FFFFFFFF", borderColor1),
                Pair("#FF17BFC5", borderColor2),
                Pair("#FF4CAF50", borderColor3),
                Pair("#FFFF0000", borderColor4),
                Pair("#FFFFFB00", borderColor5)
            )

            colorItems.forEach { (itemColor, borderColor) ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clickable {
                            color = itemColor
                            colorItems.forEach { (_, border) ->
                                border.value = if (border == borderColor) BorderStroke(
                                    2.dp,
                                    Color.Black
                                ) else BorderStroke(2.dp, Color.White)
                            }
                        },
                    backgroundColor = Color(android.graphics.Color.parseColor(itemColor)),
                    shape = CircleShape,
                    elevation = 5.dp,
                    border = borderColor.value
                ) {

                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            enabled = true,
            textStyle = TextStyle(Color.Black),
            placeholder = { Text(text = "Some Title", fontSize = 14.sp) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = textFieldColors
        )

        TextField(
            value = subtitle.value,
            onValueChange = { subtitle.value = it },
            enabled = true,
            textStyle = TextStyle(Color.Black),
            placeholder = { Text(text = "Some Subtitle", fontSize = 14.sp) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            colors = textFieldColors
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                addNote(
                    TodoItem(
                        itemId = 0,
                        title = title.value,
                        subtitle = subtitle.value,
                        time = currentDate,
                        color = color
                    )
                )
                navigateToMainScreen()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_save_24),
                contentDescription = null
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAddScreen() {
    AddScreen(addNote = {}) {

    }
}
