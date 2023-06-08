package com.vangelnum.room.presentation.addScreen

import com.vangelnum.room.common.UiText

sealed class AddState {
    object None : AddState()
    data class Error(val errorMessage: UiText) : AddState()
    object Success : AddState()
}