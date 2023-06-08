package com.vangelnum.room.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicError(val dynamicError: String): UiText()

    class StringResource(
        @StringRes val resId: Int,
    ): UiText()

    @Composable
    fun asString(): String {
       return when(this) {
           is DynamicError -> {
               dynamicError
           }
           is StringResource -> {
               stringResource(resId)
           }
       }
    }

    fun asString(context: Context): String {
        return when(this) {
            is DynamicError -> {
                dynamicError
            }
            is StringResource -> {
                context.getString(resId)
            }
        }
    }
}