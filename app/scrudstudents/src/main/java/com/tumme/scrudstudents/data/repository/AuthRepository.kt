package com.tumme.scrudstudents.data.repository

import com.tumme.scrudstudents.data.local.dao.UserDao
import com.tumme.scrudstudents.data.local.model.UserEntity
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userDao: UserDao,
) {
    suspend fun login(user: UserEntity){
        userDao.getUserByName(user.firstName,user.lastName)
    }

    suspend fun signUp(user: UserEntity){
        userDao.insert(user)
    }


}