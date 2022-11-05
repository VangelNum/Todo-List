package com.vangelnum.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import com.vangelnum.room.ui.theme.RoomTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mv by viewModels<TodoViewModel>()
            var items = mv.readAllData.observeAsState(listOf()).value
            RoomTheme {
                    Column() {
                        Button(onClick = {
                            mv.addTodo(TodoItem(itemId = 0,itemName = "yep", isDone = true))
                        }) {

                        }
                        LazyColumn() {
                            items(items) {
                                Button(onClick = { /*TODO*/ }) {
                                    Text(text = it.itemName)
                                }
                            }
                        }            
                    }
            
            }
        }
    }
}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun TodoList(
//    list: List<TodoItem>,
//    mTodoViewModel: TodoViewModel,
//) {
//    val context = LocalContext.current
//    LazyColumn {
//        items(list) { todo ->
//            val name = rememberSaveable { mutableStateOf(todo.isDone) }
//
//            ListItem(
//                text = { Text(text = todo.itemName) },
//                icon = {
//                    IconButton(onClick = {
//                        mTodoViewModel.deleteTodo(todo)
//                    }) {
//                        Icon(
//                            Icons.Default.Delete,
//                            contentDescription = null
//                        )
//                    }
//                },
//                trailing = {
//                    Checkbox(
//                        checked = name.value,
//                        onCheckedChange = {
//                            name.value = it
//                            todo.isDone = name.value
//                            mTodoViewModel.updateTodo(todo)
//                            Toast.makeText(context, "Updated todo!", Toast.LENGTH_SHORT).show()
//                        },
//                    )
//                }
//            )
//        }
//    }
//}

//@Composable
//fun CustomCardState(
//    navController: NavController,
//    mTodoViewModel: TodoViewModel
//) {
//    Column(
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//        ) {
//            Button(onClick = { navController.navigate() }) {
//                Text(text = "Ådd Todo")
//            }
//            Button(onClick = { mTodoViewModel.deleteAllTodos() }) {
//                Text(text = "Clear all")
//            }
//        }
//    }
//}
