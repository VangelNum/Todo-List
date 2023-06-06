package com.vangelnum.room.di

import android.content.Context
import androidx.room.Room
import com.vangelnum.room.data.network.TodoDao
import com.vangelnum.room.data.network.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        synchronized(this) {
            return Room.databaseBuilder(
                context,
                TodoDatabase::class.java,
                "todo_list_database"
            ).fallbackToDestructiveMigration().build()
        }
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }
}