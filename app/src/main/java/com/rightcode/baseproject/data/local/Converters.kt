package com.rightcode.baseproject.data.local

import androidx.room.TypeConverter
import com.rightcode.baseproject.model.local.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {
    /** Need to find out how to inject moshi into this typeconverters class without creating a new moshi **/
    private var moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    //User
    private val userAdapter = moshi.adapter(User::class.java)
    @TypeConverter
    fun stringToU(string: String): User?{
        return userAdapter.fromJson(string)
    }

    @TypeConverter
    fun toJson(user: User): String {
        return userAdapter.toJson(user)
    }
}