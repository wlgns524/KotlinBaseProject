package com.rightcode.basekProject.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.ViewDataBinding
import com.rightcode.baseproject.R
import com.rightcode.baseproject.ui.base.BaseViewModel
import com.rightcode.baseproject.ui.dialog.CommonDialog

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> :
    AppCompatActivity(),
    BaseViewInterface {

    protected abstract val mViewModel: VM
    protected lateinit var mViewDataBinding: DB
    private var loadingDialog: AppCompatDialog? = null
    private var commonDialog: CommonDialog? = null

    open fun initialize() {}

    @LayoutRes
    abstract fun getLayoutRes(): Int
    abstract fun getDataViewBinding(): DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = getDataViewBinding()
    }

    override fun showLoading() {
        showLoading(true)
    }

    override fun showLoading(cancelable: Boolean?) {
        showLoading(cancelable, false)
    }

    override fun showLoading(cancelable: Boolean?, canceledOnTouchOutside: Boolean?) {
        showLoading(cancelable, canceledOnTouchOutside, true)
    }

    override fun showLoading(
        cancelable: Boolean?,
        canceledOnTouchOutside: Boolean?,
        cancelAction: Boolean?
    ) {
        if (isFinishing) return //activity 가 종료중이면 return 처리

        if (loadingDialog == null) {
            loadingDialog = AppCompatDialog(this).apply {
                loadingDialog?.setCanceledOnTouchOutside(canceledOnTouchOutside!!)
                loadingDialog?.setCancelable(cancelable!!)
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setContentView(R.layout.dialog_loading)
            }
            loadingDialog?.show()
        }
    }

    override fun hideLoading() {
        if (loadingDialog == null) return // 이미 닫혀있으면 무시

        if (isLoading())
            loadingDialog?.dismiss()

        loadingDialog = null
    }

    override fun isLoading(): Boolean {
        return loadingDialog?.isShowing()!!
    }

    override fun finishActivity() {
        finish()
        overridePendingTransition(
            R.anim.slide_in_activity_from_left,
            R.anim.slide_out_activity_to_right
        )
    }

    override fun showErrorDialog(message: String?) {
        showErrorDialog(message, onClick = null)
    }

    override fun showErrorDialog(title: String?, message: String?) {
        showErrorDialog(title, message, null)
    }

    override fun showErrorDialog(message: String?, onClick: (() -> Unit)?) {
        showErrorDialog(null, message, onClick)
    }

    override fun showErrorDialog(title: String?, message: String?, onClick: (() -> Unit)?) {
        if (isFinishing) return //activity 가 종료중이면 return 처리

        if (commonDialog == null)
            commonDialog = CommonDialog(this).apply {
                window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setContentView(R.layout.dialog_common)
            }
        commonDialog?.setTitle(title)
        commonDialog?.setMessage(message)
        commonDialog?.setPositiveButton(getString(R.string.ok), onClick)
        commonDialog?.show()

    }
}