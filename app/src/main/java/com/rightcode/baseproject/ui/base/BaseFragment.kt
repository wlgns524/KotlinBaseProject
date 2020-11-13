package com.rightcode.baseProject.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.rightcode.basekProject.ui.BaseActivity
import com.rightcode.basekProject.ui.BaseViewInterface
import com.rightcode.baseproject.ui.base.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> :
    Fragment(), BaseViewInterface {

    protected abstract val mViewModel: VM
    protected lateinit var mViewDataBinding: DB

    open fun initialize() {}

    @LayoutRes
    abstract fun getLayoutRes(): Int
    abstract fun getDataViewBinding(): DB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewDataBinding = getDataViewBinding()
    }

    override fun showLoading() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showLoading()
        }
    }

    override fun showLoading(cancelable: Boolean?) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showLoading(cancelable)
        }
    }

    override fun showLoading(cancelable: Boolean?, canceledOnTouchOutside: Boolean?) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showLoading(cancelable, canceledOnTouchOutside)
        }
    }

    override fun showLoading(
        cancelable: Boolean?,
        canceledOnTouchOutside: Boolean?,
        cancelAction: Boolean?
    ) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showLoading(
                cancelable,
                canceledOnTouchOutside,
                cancelAction
            )
        }
    }

    override fun hideLoading() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).hideLoading()
        }
    }

    override fun isLoading(): Boolean {
        if (activity is BaseActivity<*, *>) {
            return (activity as BaseActivity<*, *>).isLoading()
        }
        return false
    }

    override fun finishActivity() {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).finishActivity()
        }
    }

    override fun showErrorDialog(message: String?) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showErrorDialog(message)
        }
    }

    override fun showErrorDialog(title: String?, message: String?) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showErrorDialog(title, message)
        }
    }

    override fun showErrorDialog(message: String?, onClick: (() -> Unit)?) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showErrorDialog(message, onClick)
        }
    }

    override fun showErrorDialog(title: String?, message: String?, onClick: (() -> Unit)?) {
        if (activity is BaseActivity<*, *>) {
            (activity as BaseActivity<*, *>).showErrorDialog(title, message, onClick)
        }
    }
}