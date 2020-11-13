package com.rightcode.baseproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rightcode.baseproject.data.local.dao.UserDao
import com.rightcode.baseproject.model.local.User


//@Database(entities = [WeatherInfo::class, LocationLatLng::class], version = 1, exportSchema = false)
@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
