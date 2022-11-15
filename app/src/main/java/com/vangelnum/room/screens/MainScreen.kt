package com.vangelnum.room.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vangelnum.room.R
import com.vangelnum.room.TodoViewModel
import com.vangelnum.room.navigation.Screens
import kotlinx.coroutines.launch


fun Color.Companion.parse(colorString: String): Color =
    Color(color = android.graphics.Color.parseColor(colorString))

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(viewmodel: TodoViewModel, navController: NavController) {
    val items = viewmodel.readAllData.observeAsState(listOf()).value


    var visibleSearchBar by remember {
        mutableStateOf(false)
    }
    var visiblecurrentSearch by remember {
        mutableStateOf(true)
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    var searchtext by remember {
        mutableStateOf(false)
    }

    var query by remember {
        mutableStateOf("")
    }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screens.AddScreen.route) },
                backgroundColor = Color.White,
                contentColor = Color.Black
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                    contentDescription = "add")
            }
        },
        drawerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(60.dp),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "MADE BY VANGELNUM", fontSize = 24.sp, textAlign = TextAlign.Center
                )
            }
        },
        topBar = {
            TopAppBar(
                actions = {
                    LaunchedEffect(visibleSearchBar) {
                        if (visibleSearchBar) {
                            focusRequester.requestFocus()
                            keyboardController?.show()
                        }

                    }
                    val state = remember { mutableStateOf(TextFieldValue("")) }

                    AnimatedVisibility(visible = visiblecurrentSearch) {
                        IconButton(onClick = {
                            visiblecurrentSearch = false
                            visibleSearchBar = true
                            searchtext = true

                        }) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24),
                                contentDescription = "search", tint = Color.White)
                        }

                    }
                    AnimatedVisibility(
                        visible = visibleSearchBar,

                        ) {
                        TextField(
                            modifier = Modifier
                                .focusRequester(focusRequester)
                                .fillMaxWidth()
                                .padding(end = 10.dp)
                                .scale(scaleX = 1F, scaleY = 1F),
                            value = state.value,
                            onValueChange = { value ->
                                state.value = value
                            },
                            enabled = true,
                            shape = RoundedCornerShape(25.dp),

                            placeholder = {
                                Text(
                                    text = "Search",
                                    fontSize = 14.sp
                                )
                            },
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (state.value.text != "")
                                        query = state.value.text
                                }
                            ),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_search_24),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        state.value = TextFieldValue("")
                                        query = ""
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_baseline_close_24),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                }

                            },
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.White,
                                cursorColor = Color.White,
                                leadingIconColor = Color.White,
                                trailingIconColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.White
                            )
                        )
                    }


                },
                title = {

                },
                navigationIcon = {
                    if (!searchtext) {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                                contentDescription = "menu")

                        }
                    } else {
                        IconButton(onClick = {
                            visibleSearchBar = false
                            visiblecurrentSearch = true
                            searchtext = false
                            keyboardController?.hide()
                            query = ""
                        }) {
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                                contentDescription = "menu")

                        }
                    }
                },
                backgroundColor = colorResource(id = R.color.white).copy(alpha = 0f),
                contentColor = Color.White
            )
        }
    ) {

        if (query == "") {
            LazyColumn(modifier = Modifier.padding(all = 10.dp),
                contentPadding = PaddingValues(bottom = 60.dp)) {

                items(items) { item ->
                    Card(Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                        backgroundColor = Color.parse(item.color),
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
                                                item.color
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
        } else {
            LazyColumn(modifier = Modifier.padding(all = 10.dp)) {
                items(items) { item ->
                    if (item.title.contains(query, ignoreCase = true) || item.subtitle.contains(
                            query,
                            ignoreCase = true)
                    ) {
                        Card(Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                            backgroundColor = Color.parse(item.color),
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
                                                    item.color
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
    }

}

