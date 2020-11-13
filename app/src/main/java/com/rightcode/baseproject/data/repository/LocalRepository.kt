package com.rightcode.baseproject.data.repository

import com.rightcode.baseproject.data.local.dao.UserDao
import com.rightcode.baseproject.model.local.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: User): Long {
        return userDao.insert(user)
    }

    suspend fun getUsers(userIds: IntArray): List<User> {
        return userDao.loadAllByIds(userIds)
    }

    suspend fun getAll(): List<User> {
        return userDao.getAll()
    }
}