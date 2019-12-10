package com.farouk.travelcar.dagger

import android.app.Application
import dagger.Provides
import androidx.room.Room
import com.farouk.travelcar.data.dao.UserDao
import com.farouk.travelcar.data.database.AppDataBase
import com.farouk.travelcar.data.repository.UserDataSource
import com.farouk.travelcar.data.repository.UserRepository
import dagger.Module
import javax.inject.Singleton


@Module
class RoomModule(mApplication: Application) {

    private val appDatabase: AppDataBase =
        Room.databaseBuilder(mApplication, AppDataBase::class.java!!, "demo-db").build()

    @Singleton
    @Provides
    internal fun providesRoomDatabase(): AppDataBase {
        return appDatabase
    }

    @Singleton
    @Provides
    internal fun providesUserDao(demoDatabase: AppDataBase): UserDao {
        return demoDatabase.getUserDao()
    }

    @Singleton
    @Provides
    internal fun UserRepository(productDao: UserDao): UserRepository {
        return UserDataSource(productDao)
    }

}