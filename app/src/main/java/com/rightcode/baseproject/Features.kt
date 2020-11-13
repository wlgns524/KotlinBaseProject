package com.rightcode.baseproject

import android.content.Context
import com.rightcode.baseproject.model.EnumModel
import com.rightcode.baseproject.util.extension.LogD
import com.rightcode.baseproject.util.helper.PreferenceHelper


object Features {

    const val DATABASE_NAME = "ROOM_TEST_DATABASE"

    enum class ConnectServer {
        REAL, QA
    }

    val CONNECT_COUNTRY: EnumModel.Country? = null
    val SERVICE_COUNTRY: EnumModel.Country? = null
    const val TEST_ONLY = true

    /**
     * Log.java 클래스의 메소드를 사용한 로그를 출력할지 여부
     */
    const val SHOW_LOG = true

    /**
     * 네트워크 관련 로그를 출력할지 여부
     */
    const val SHOW_NETWORK_LOG = true

    fun getServer(context: Context): ConnectServer? {
        val name: String? = PreferenceHelper.getInstance(context)
            ?.get(PreferenceHelper.PreferenceKey.ConnectServer, ConnectServer.REAL.name)
        val server: ConnectServer
        server = try {
            ConnectServer.valueOf(name!!)
        } catch (e: Exception) {
//            Log.e(e)
            ConnectServer.REAL
        }
        LogD(server.name)
        return server
    }
}
