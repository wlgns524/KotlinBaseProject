package com.rightcode.baseproject.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rightcode.baseproject.model.local.User.Companion.TABLE_NAME
import com.squareup.moshi.Json

@Entity(tableName = TABLE_NAME)
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0,
    @ColumnInfo(name = "first_name")
    @Json(name = "FirstName")
    val firstName: String?,
    @ColumnInfo(name = "last_name")
    @Json(name = "LastName")
    val lastName: String?
) {
    companion object {
        const val TABLE_NAME = "user"
    }
}
