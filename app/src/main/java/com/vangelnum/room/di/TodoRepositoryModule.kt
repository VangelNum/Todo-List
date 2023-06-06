package com.vangelnum.room.di

import com.vangelnum.room.data.repository.TodoRepositoryImpl
import com.vangelnum.room.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TodoRepositoryModule {
    @Binds
    @Singleton
    fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository
}