package com.rightcode.baseproject.util.extension

import android.util.Log
import com.rightcode.baseproject.Features


/**
 * - v : 기타 로그 메시지
 * - d : 개발 중에만 유용한 디버그 로그 메시지
 * - i : 일반적인 사용에 대해 예상할 수 있는 로그 메시지
 * - w : 아직 오류는 아니지만 발생 가능한 문제
 * - e : 오류를 일으킨 문제
 */

const val TAG = "LogTag"


fun LogV(message: String) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(message))
    }
}

fun LogV(message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(null, message))
    }
}

fun LogV(format: String?, message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(format, message))
    }
}

fun LogD(message: String) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(message))
    }
}

fun LogD(message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(null, message))
    }
}

fun LogD(format: String?, message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(format, message))
    }
}

fun LogI(message: String) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(message))
    }
}

fun LogI(message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(null, message))
    }
}

fun LogI(format: String?, message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(format, message))
    }
}

fun LogW(message: String) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(message))
    }
}

fun LogW(message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(null, message))
    }
}

fun LogW(format: String?, message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(format, message))
    }
}

fun LogE(message: String) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(message))
    }
}

fun LogE(message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(null, message))
    }
}

fun LogE(format: String?, message: Any) {
    if (Features.TEST_ONLY && Features.SHOW_LOG) {
        Log.d(TAG, buildMessage(format, message))
    }
}

private fun buildMessage(format: String?, vararg message: Any): String {
    val stackTraceElement = Thread.currentThread().stackTrace[4]
    val sb = StringBuilder()
    sb.append("[")
    sb.append(stackTraceElement.fileName)
    sb.append(" ")
    sb.append(stackTraceElement.lineNumber)
    if (format == null || format.isEmpty()) {
        if (message == null || message.isEmpty()) {
            sb.append("] >> ")
            sb.append(stackTraceElement.methodName)
        } else if (message.size > 1) {
            sb.append("] ")
            sb.append(message)
        } else {
            sb.append("] ")
            sb.append(message[0])
        }
    } else if (message == null || message.isEmpty()) {
        sb.append("] ")
        sb.append(format)
    } else {
        sb.append("] ")
        sb.append(String.format(format, *message))
    }
    return sb.toString()
}