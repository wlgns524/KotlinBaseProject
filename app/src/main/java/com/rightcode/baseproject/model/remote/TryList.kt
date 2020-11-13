package com.rightcode.baseproject.model.remote

data class TryList(
    var rec: Long,
    var menus: List<Menu>,
    var items: List<String>?,
    var sections: String?

)