package com.vangelnum.room.feature_note.domain.use_case

data class NoteUseCases(
    val getNotesUseCase: GetNotesUseCase,
    val addNoteUseCase: InsertNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getNote: GetNoteUseCase
)
