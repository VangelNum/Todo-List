package com.vangelnum.room.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vangelnum.room.R
import com.vangelnum.room.common.UiText
import com.vangelnum.room.data.model.TodoItem
import com.vangelnum.room.data.repository.TodoRepositoryImpl
import com.vangelnum.room.presentation.addScreen.AddState
import com.vangelnum.room.presentation.updateScreen.UpdateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepositoryImpl
) : ViewModel() {

    var allNotes: LiveData<List<TodoItem>> = todoRepository.getAllNotes()

    private val _addState = MutableSharedFlow<AddState>()
    val addState = _addState.asSharedFlow()

    private val _updateState = MutableSharedFlow<UpdateState>()
    val updateState = _updateState.asSharedFlow()

    fun addTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            try {
                if (todoItem.title.isNotEmpty() && todoItem.subtitle.isNotEmpty()) {
                    todoRepository.addTodo(todoItem)
                    _addState.emit(AddState.Success)
                } else {
                    _addState.emit(AddState.Error(UiText.StringResource(R.string.error_add_message)))
                }
            } catch (e: Exception) {
                _addState.emit(AddState.Error(UiText.DynamicError(e.message.toString())))
            }
        }
    }

    fun updateTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            try {
                if (todoItem.title.isNotEmpty() && todoItem.subtitle.isNotEmpty()) {
                    todoRepository.updateTodo(todoItem)
                    _updateState.emit(UpdateState.Success)
                } else {
                    _updateState.emit(UpdateState.Error(UiText.StringResource(R.string.error_add_message)))
                }
            } catch (e: Exception) {
                _updateState.emit(UpdateState.Error(UiText.DynamicError(e.message.toString())))
            }
        }
    }

    fun deleteTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todoItem = todoItem)
        }
    }

    fun deleteAllTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteAllTodos()
        }
    }

}

