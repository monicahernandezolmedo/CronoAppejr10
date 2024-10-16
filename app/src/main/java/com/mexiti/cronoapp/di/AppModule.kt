package com.mexiti.cronoapp.di

import android.content.Context
import androidx.room.Room
import com.mexiti.cronoapp.room.CronosDataBase
import com.mexiti.cronoapp.room.CronosDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Singleton
    @Provides
    fun providesCronosDao(cronoDataBase: CronosDataBase):CronosDatabaseDao{
        return cronoDataBase.cronosDao()
    }

    @Singleton
    @Provides
    fun providesCronosDatabase(@ApplicationContext context: Context):CronosDataBase{
        return Room.databaseBuilder(
            context= context,
            CronosDataBase::class.java,
            name = "cronos_db"
        ).fallbackToDestructiveMigration()
            .build()
    }


}



