package com.vangelnum.room.presentation.updateScreen

import com.vangelnum.room.common.UiText

sealed class UpdateState {
    object None : UpdateState()
    data class Error(val errorMessage: UiText) : UpdateState()
    object Success : UpdateState()
}