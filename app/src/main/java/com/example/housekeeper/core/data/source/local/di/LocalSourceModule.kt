package com.example.housekeeper.core.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.example.housekeeper.core.data.repository.LocalDataSourceRepositoryImpl
import com.example.housekeeper.core.data.source.local.dataSourse.ReminderLocalDataSource
import com.example.housekeeper.core.data.source.local.dataSourse.TimerLocalDataSource
import com.example.housekeeper.core.data.source.local.dataSourse.room.ReminderRoomDataSource
import com.example.housekeeper.core.data.source.local.dataSourse.room.TimerRoomDataSource
import com.example.housekeeper.core.data.source.local.db.HouseKeeperRoomDatabase
import com.example.housekeeper.core.domain.repository.LocalDataSourceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalSourceModuleProvider {

    @Provides
    fun provideReminderDao(database: HouseKeeperRoomDatabase) = database.reminderDao()

    @Provides
    fun provideTimerDao(database: HouseKeeperRoomDatabase) = database.timerDao()


    @Provides
    @Singleton
    fun providesLocalDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        HouseKeeperRoomDatabase::class.java,
        "House Keeper database"
    ).build()
}


@Module
@InstallIn(SingletonComponent::class)
abstract class LocalSourceModuleBuilder {

    @Binds
    abstract fun bindReminderDataSource(
        reminderRoomDataSource: ReminderRoomDataSource
    ): ReminderLocalDataSource

    @Binds
    abstract fun bindTimerDataSource(
        timerRoomDataSource: TimerRoomDataSource
    ): TimerLocalDataSource

    @Binds
    abstract fun bindDefaultJustHouseKeeperRepository(
        defaultHouseKeeperRepository: LocalDataSourceRepositoryImpl
    ) : LocalDataSourceRepository
}


