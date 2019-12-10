package com.farouk.travelcar.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.farouk.travelcar.data.dao.UserDao
import com.farouk.travelcar.data.model.UserResponse

@Database(entities = [UserResponse::class], version = 3)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

}


