package com.vangelnum.room.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vangelnum.room.R
import com.vangelnum.room.TodoViewModel
import com.vangelnum.room.navigation.Screens
import com.vangelnum.room.ui.theme.mycolor
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Composable
fun MainScreen(viewmodel: TodoViewModel, navController: NavController) {
    val items = viewmodel.readAllData.observeAsState(listOf()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screens.DetailScreen.route) }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "add")
            }
        },
        topBar = {
            TopAppBar {

            }
        }
    ) {
        LazyColumn() {
           items(items) { item->
               val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
               val currentDate = sdf.format(Date())
               Log.d("check", "C DATE is  "+currentDate)
               Card(Modifier
                   .fillMaxWidth()
                   .padding(10.dp),
                   backgroundColor = Color.Cyan,
                   elevation = 5.dp,
                   shape = RoundedCornerShape(25)
               ) {
                   Column(modifier = Modifier.padding(10.dp)) {
                       Text(text = item.title, fontSize = 20.sp)
                       Text(text = item.subtitle, fontSize = 20.sp)
                       Icon(
                           painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                           contentDescription = "delete",
                           tint = Color.Black,
                           modifier = Modifier.align(Alignment.End)
                       )
                   }
               }

           }
        }
    }
}

//
//@Composable
//fun MainScreen(viewmodel: TodoViewModel, navController: NavController) {
//    val items = viewmodel.readAllData.observeAsState(listOf()).value
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(onClick = { navController.navigate(Screens.DetailScreen.route) }) {
//                Icon(painter = painterResource(id = R.drawable.ic_baseline_add_24),
//                    contentDescription = "add")
//            }
//        },
//        topBar = {
//            TopAppBar {
//
//            }
//        }
//    ) {
//        Column {
//            Text(
//                text = "Your Note",
//                fontSize = 35.sp,
//                fontWeight = FontWeight.Medium,
//                modifier = Modifier.padding(10.dp)
//            )
//            LazyColumn {
//                items(items) { item ->
//                    Card(modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(10.dp),
//                        elevation = 5.dp,
//                        shape = RoundedCornerShape(25),
//                        backgroundColor = MaterialTheme.colors.mycolor
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(10.dp)
//                                .clickable {
//                                    navController.navigate(Screens.UpdateScreen.withArgs(item.itemId.toString(),
//                                        item.title,
//                                        item.subtitle))
//                                }
//                        ) {
//
//                            Text(
//                                text = item.title,
//                                textAlign = TextAlign.Start,
//                                fontSize = 20.sp,
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .padding(all = 5.dp)
//
//                            )
//                            Text(
//                                text = item.subtitle,
//                                textAlign = TextAlign.Start,
//                                fontSize = 20.sp,
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .padding(all = 5.dp)
//                            )
////                            IconButton(onClick = {
////                                viewmodel.deleteTodo(item)
////                            }, modifier = Modifier.align(Alignment.Bottom)) {
////                                Icon(
////                                    painter = painterResource(id = R.drawable.ic_baseline_delete_24),
////                                    contentDescription = "delete",
////                                    tint = Color.Black
////                                )
////                            }
//
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//}
