package com.farouk.travelcar.data.repository

import androidx.lifecycle.LiveData
import com.farouk.travelcar.data.model.UserResponse


interface UserRepository  {

     fun findAll(): LiveData<List<UserResponse>>

      fun insert(userResponse: UserResponse):Long

     fun getUser(): LiveData<UserResponse>



}