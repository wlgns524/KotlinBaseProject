package com.rightcode.basekProject.ui

import com.rightcode.baseproject.ui.dialog.CommonDialog

interface BaseViewInterface {

    fun showLoading()

    fun showLoading(cancelable: Boolean?)

    fun showLoading(cancelable: Boolean?, canceledOnTouchOutside: Boolean?)

    fun showLoading(cancelable: Boolean?, canceledOnTouchOutside: Boolean?, cancelAction: Boolean?)

    fun hideLoading()

    fun isLoading(): Boolean

    fun finishActivity()

    fun showErrorDialog(message: String?)

    fun showErrorDialog(title: String?, message: String?)

    fun showErrorDialog(message: String?, onClick: (() -> Unit)?)

    fun showErrorDialog(title: String?, message: String?, onClick: (() -> Unit)?)
}