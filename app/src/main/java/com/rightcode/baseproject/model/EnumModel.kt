package com.rightcode.baseproject.model

import android.text.TextUtils
import com.rightcode.baseproject.R

class EnumModel {
    enum class Country(
        var phoneCodeResourceId: Int,
        var labelResourceId: Int,
        var number: String
    ) {
        US(R.string.phone_code_us, R.string.usa, "1"),
        KR(R.string.phone_code_kr, R.string.korea, "82"),
        CN(R.string.phone_code_cn, R.string.china, "86"),
        SG(R.string.phone_code_sg, R.string.singapore, "65");

        companion object {
            fun getEnum(value: String?): Country? {
                if (TextUtils.isEmpty(value)) {
                    return null
                }
                for (resultCode in values()) {
                    if (resultCode.toString().equals(value, ignoreCase = true)) {
                        return resultCode
                    }
                }
                return null
            }
        }
    }
}