package com.farouk.travelcar.data.repository

import androidx.lifecycle.LiveData
import com.farouk.travelcar.data.dao.UserDao
import com.farouk.travelcar.data.model.UserResponse

class UserDataSource( private val userDao: UserDao) : UserRepository {
    override fun getUser(): LiveData<UserResponse> {
        return userDao.getUser()
    }

    override  fun findAll(): LiveData<List<UserResponse>> {
        return userDao.findAll()
    }

    override  fun insert(userResponse: UserResponse):Long {
        return userDao.insertUser(userResponse)
    }

}