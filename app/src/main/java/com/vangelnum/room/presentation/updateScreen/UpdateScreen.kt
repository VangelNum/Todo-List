package com.vangelnum.room.presentation.updateScreen

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
fun UpdateScreen(
    id: Int?,
    title: String?,
    subtitle: String?,
    color: String?,
    updateTodo: (todoItem: TodoItem) -> Unit,
    navigateToMainScreen: () -> Unit,
    updateState: UpdateState
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = updateState) {
        handleUpdateState(updateState, context, navigateToMainScreen)
    }

    val currentDate = getCurrentDate()
    var updatedColor by rememberSaveable { mutableStateOf(color) }
    val updatedTitle = rememberSaveable { mutableStateOf(title) }
    val updatedSubtitle = rememberSaveable { mutableStateOf(subtitle) }
    val textFieldColors = getTextFieldColorsUpdate()
    val borderColorItems = remember { getBorderColorItems() }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        ShowColorPickerUpdateScreen(borderColorItems) { selectedColor ->
            updatedColor = selectedColor
        }
        Spacer(modifier = Modifier.height(10.dp))
        ShowTitleTextFieldUpdateScreen(updatedTitle, textFieldColors)
        ShowSubtitleTextFieldUpdateScreen(updatedSubtitle, textFieldColors)
    }

    ShowFloatingActionButton {
        updateTodo(
            TodoItem(
                itemId = id ?: 0,
                title = updatedTitle.value ?: "Empty Title",
                updatedSubtitle.value ?: "Empty Subtitle",
                currentDate,
                color = updatedColor ?: "#FF17BFC5"
            )
        )
    }
}

private fun handleUpdateState(
    addState: UpdateState,
    context: Context,
    navigateToMainScreen: () -> Unit
) {
    when (addState) {
        is UpdateState.Error -> {
            showToast(context, addState.errorMessage.asString(context))
        }

        is UpdateState.Success -> {
            navigateToMainScreen()
        }

        UpdateState.None -> Unit
    }
}

private fun getCurrentDate(): String {
    val sdf: DateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.US)
    return sdf.format(Date())
}

@Composable
private fun getTextFieldColorsUpdate(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        textColor = Color.Black,
        cursorColor = Color.Black,
        leadingIconColor = Color.Black,
        trailingIconColor = Color.Black,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Black,
        backgroundColor = Color.White
    )
}

private fun getBorderColorItems(): List<MutableState<BorderStroke>> {
    return listOf(
        mutableStateOf(BorderStroke(2.dp, Color.Black)),
        mutableStateOf(BorderStroke(2.dp, Color.Transparent)),
        mutableStateOf(BorderStroke(2.dp, Color.Transparent)),
        mutableStateOf(BorderStroke(2.dp, Color.Transparent)),
        mutableStateOf(BorderStroke(2.dp, Color.Transparent))
    )
}

@Composable
private fun ShowColorPickerUpdateScreen(
    borderColorItems: List<MutableState<BorderStroke>>,
    onColorSelected: (String) -> Unit
) {
    val colorItems = listOf(
        "#FFFFFFFF",
        "#FF17BFC5",
        "#FF4CAF50",
        "#FFFF0000",
        "#FFFFFB00"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        colorItems.forEachIndexed { index, itemColor ->
            Card(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clickable {
                        onColorSelected(itemColor)
                        borderColorItems.forEachIndexed { i, borderColor ->
                            borderColor.value = if (i == index) BorderStroke(2.dp, Color.Black)
                            else BorderStroke(2.dp, Color.White)
                        }
                    },
                backgroundColor = Color(android.graphics.Color.parseColor(itemColor)),
                shape = CircleShape,
                elevation = 5.dp,
                border = borderColorItems[index].value
            ) {

            }
        }
    }
}

@Composable
private fun ShowTitleTextFieldUpdateScreen(
    title: MutableState<String?>,
    textFieldColors: TextFieldColors
) {
    TextField(
        value = title.value ?: "",
        onValueChange = { title.value = it },
        enabled = true,
        textStyle = TextStyle(Color.Black),
        placeholder = { Text(text = "Some Title", fontSize = 14.sp) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        colors = textFieldColors
    )
}

@Composable
private fun ShowSubtitleTextFieldUpdateScreen(
    subtitle: MutableState<String?>,
    textFieldColors: TextFieldColors
) {
    TextField(
        value = subtitle.value?: "",
        onValueChange = { subtitle.value = it },
        enabled = true,
        textStyle = TextStyle(Color.Black),
        placeholder = { Text(text = "Some Subtitle", fontSize = 14.sp) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = false,
        colors = textFieldColors
    )
}

@Composable
private fun ShowFloatingActionButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                onClick()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_save_24),
                contentDescription = null
            )
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewUpdateScreen() {
    UpdateScreen(
        id = 0,
        title = "title",
        subtitle = "subtitle",
        color = "#FF17BFC5",
        updateTodo = {},
        navigateToMainScreen = {},
        updateState = UpdateState.None
    )
}
