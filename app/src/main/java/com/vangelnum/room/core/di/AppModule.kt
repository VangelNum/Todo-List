package com.vangelnum.room.core.di

import android.content.Context
import androidx.room.Room
import com.vangelnum.room.feature_note.data.data_source.NoteDatabase
import com.vangelnum.room.feature_note.data.repository.NoteRepositoryImpl
import com.vangelnum.room.feature_note.domain.repository.NoteRepository
import com.vangelnum.room.feature_note.domain.use_case.DeleteNoteUseCase
import com.vangelnum.room.feature_note.domain.use_case.GetNoteUseCase
import com.vangelnum.room.feature_note.domain.use_case.GetNotesUseCase
import com.vangelnum.room.feature_note.domain.use_case.InsertNoteUseCase
import com.vangelnum.room.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            addNoteUseCase = InsertNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }
}