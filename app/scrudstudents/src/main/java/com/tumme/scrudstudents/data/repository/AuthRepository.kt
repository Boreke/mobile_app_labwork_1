package com.tumme.scrudstudents.data.repository

import com.tumme.scrudstudents.data.local.dao.UserDao
import com.tumme.scrudstudents.data.local.model.UserEntity
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val userDao: UserDao,
) {
    suspend fun login(user: UserEntity): UserEntity? {
        return try {
            userDao.getUserByName(user.firstName, user.lastName)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signUp(user: UserEntity){
        userDao.insert(user)
    }


}