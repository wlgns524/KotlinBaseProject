package com.rightcode.baseproject.model.remote

import com.squareup.moshi.Json

data class Menu(

    var title: String?,
    @Json(name = "imgUrl")
    var img_url: String?,
    var imgIconUrl: String?,
    var link: String?,

)
