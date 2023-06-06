package com.vangelnum.room.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vangelnum.room.R
import com.vangelnum.room.data.model.TodoItem

@Composable
fun MainScreen(
    todoItems: List<TodoItem>,
    navigateToUpdateScreen: (
        id: Int,
        title: String,
        subtitle: String,
        color: String
    ) -> Unit,
    navigateToAddScreen: () -> Unit,
    deleteTodo: (item: TodoItem) -> Unit
) {
    var query by rememberSaveable {
        mutableStateOf("")
    }
    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 80.dp)
    ) {
        item {
            SearchTextField(query = query, onQueryChange = { query = it })
        }
        items(todoItems) { todoItem ->
            TodoItemsList(
                todoItems = todoItem,
                query = query,
                navigateToUpdateScreen = navigateToUpdateScreen,
                deleteTodo = deleteTodo
            )
        }
    }
    AddButton(onClick = navigateToAddScreen)
}

@Composable
fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        value = query,
        onValueChange = onQueryChange,
        shape = MaterialTheme.shapes.large,
        trailingIcon = {
            Icon(Icons.Filled.Edit, contentDescription = null)
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search_your_note))
        }
    )
}

@Composable
fun TodoItemsList(
    todoItems: TodoItem,
    query: String,
    navigateToUpdateScreen: (
        id: Int,
        title: String,
        subtitle: String,
        color: String
    ) -> Unit,
    deleteTodo: (item: TodoItem) -> Unit
) {
    if (query.isNotEmpty() && todoItems.title.contains(query) || todoItems.subtitle.contains(query)) {
        Card(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            backgroundColor = Color(android.graphics.Color.parseColor(todoItems.color)),
            elevation = 5.dp,
            shape = RoundedCornerShape(15)
        ) {
            TodoItemRow(
                item = todoItems,
                navigateToUpdateScreen = navigateToUpdateScreen,
                deleteTodo = deleteTodo
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoItemRow(
    item: TodoItem,
    navigateToUpdateScreen: (
        id: Int,
        title: String,
        subtitle: String,
        color: String
    ) -> Unit,
    deleteTodo: (item: TodoItem) -> Unit
) {
    Row(
        modifier = Modifier.padding(all = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    navigateToUpdateScreen(
                        item.itemId,
                        item.title,
                        item.subtitle,
                        item.color
                    )
                }
        ) {
            Text(
                text = item.title,
                maxLines = 1,
            )
            Text(
                text = item.subtitle,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.time,
            )
        }
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            DeleteButton(
                modifier = Modifier.align(Alignment.Bottom),
                onClick = { deleteTodo(item) })
        }
    }
}

@Composable
fun DeleteButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_delete_24),
            contentDescription = stringResource(id = R.string.delete_note),
        )
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, end = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_note)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(
        todoItems = listOf(
            TodoItem(0, "title", "subtitle", time = "time", "#FF17BFC5"),
            TodoItem(0, "title", "subtitle", time = "time", "#FF17BFC5"),
            TodoItem(0, "title", "subtitle", time = "time", "#FF17BFC5"),
        ),
        navigateToUpdateScreen = { id, title, subtitle, color ->

        },
        navigateToAddScreen = { /*TODO*/ },
        deleteTodo = {

        }
    )
}

