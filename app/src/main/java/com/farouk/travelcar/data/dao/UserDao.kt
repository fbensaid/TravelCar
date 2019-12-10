package com.farouk.travelcar.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farouk.travelcar.data.model.CURRENT_USERID
import com.farouk.travelcar.data.model.UserResponse

@Dao
 interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun insertUser(user: UserResponse):Long

    @Query("SELECT * FROM UserResponse")
     fun findAll():LiveData<List<UserResponse>>

    @Query("SELECT * FROM userResponse WHERE id_db_user= $CURRENT_USERID")
     fun getUser():LiveData<UserResponse>
}