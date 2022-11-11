package com.vangelnum.room.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vangelnum.room.R
import com.vangelnum.room.TodoViewModel
import com.vangelnum.room.navigation.Screens


@Composable
fun MainScreen(viewmodel: TodoViewModel, navController: NavController) {
    val items = viewmodel.readAllData.observeAsState(listOf()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screens.DetailScreen.route) },
                backgroundColor = Color.White,
                contentColor = Color.Black
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "add")
            }
        },
        topBar = {
            TopAppBar()
        }
    ) {

        LazyColumn(modifier = Modifier.padding(all = 10.dp)) {

            items(items) { item ->
                Card(Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                    backgroundColor = Color.Cyan,
                    elevation = 5.dp,
                    shape = RoundedCornerShape(15)
                ) {
                    Row(modifier = Modifier
                        .padding(all = 17.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate(
                                        Screens.UpdateScreen.withArgs(
                                            item.itemId.toString(),
                                            item.title,
                                            item.subtitle,
                                        ),
                                    )
                                }
                        ) {
                            Text(
                                text = item.title,
                                fontSize = 20.sp,
                                color = Color.Black,
                                maxLines = 1,
                                fontWeight = FontWeight.W400
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = item.subtitle,
                                fontSize = 15.sp,
                                color = Color.Black,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = item.time,
                                fontSize = 15.sp,
                                color = Color.Black
                            )

                        }
                        IconButton(
                            onClick = { viewmodel.deleteTodo(item) },
                            modifier = Modifier
                                .align(Alignment.Bottom)
                                .offset(10.dp, 10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                                contentDescription = "delete",
                                tint = Color.Black,
                            )
                        }
                    }

                }

            }
        }
    }

}

@Composable
fun TopAppBar() {
    TopAppBar(
        title = { Text(text = "Your note") },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_dehaze_24),
                    contentDescription = "dehaze")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = "search")
            }
        }
    )
}