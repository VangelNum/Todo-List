package com.vangelnum.room.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vangelnum.room.ui.theme.BabyBlue
import com.vangelnum.room.ui.theme.LightGreen
import com.vangelnum.room.ui.theme.RedOrange
import com.vangelnum.room.ui.theme.RedPink
import com.vangelnum.room.ui.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val time: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)