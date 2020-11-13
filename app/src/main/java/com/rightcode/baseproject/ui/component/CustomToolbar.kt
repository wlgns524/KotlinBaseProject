package com.rightcode.baseproject.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.CustomToolbarBinding
import com.rightcode.baseproject.util.extension.gone
import com.rightcode.baseproject.util.extension.visible
import kotlinx.android.synthetic.main.custom_toolbar.view.*

class CustomToolbar : Toolbar {
    lateinit var binding: ViewBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initial(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        initial(context, attributeSet)
    }

    private fun initial(context: Context, attributeSet: AttributeSet) {
        val typeArray =
            context.theme.obtainStyledAttributes(attributeSet, R.styleable.CustomToolbar, 0, 0)
        var title = typeArray.getString(R.styleable.CustomToolbar_toolbarTitle) ?: ""
        var showBack = typeArray.getBoolean(R.styleable.CustomToolbar_toolbarBack, false)
        var showSearch = typeArray.getBoolean(R.styleable.CustomToolbar_toolbarSearch, false)
        var showCart = typeArray.getBoolean(R.styleable.CustomToolbar_toolbarCart, false)
        var setCartNoti = typeArray.getInt(R.styleable.CustomToolbar_toolbarCartNoti, 0)
        var showSetting = typeArray.getBoolean(R.styleable.CustomToolbar_toolbarSetting, false)
        var setSettingNoti = typeArray.getInt(R.styleable.CustomToolbar_toolbarSettingNoti, 0)

        binding = CustomToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        binding.root.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, getActionBarHeight())

        if (childCount == 0) addView(binding.root)
        setText(title)
        showBack(showBack)
        showSearch(showSearch)
        showCart(showCart)
        setCartNoti(setCartNoti)
        showSetting(showSetting)
        setSettingNoti(setSettingNoti)

        setContentInsetsAbsolute(0, 0)
        setContentInsetsRelative(0, 0)
    }

    private fun getActionBarHeight(): Int {
        val ta = context.theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        return ta.getDimension(0, 0f).toInt()
    }

    fun setOnClickListener(listener: OnCustomToolbarListener) {
        binding.root.toolbar_iv_back.setOnClickListener {
            println("listener.onClickBack()")
            listener.onClickBack() }
        binding.root.toolbar_iv_search.setOnClickListener { listener.onClickSearch() }
        binding.root.toolbar_rl_cart.setOnClickListener { listener.onClickCart() }
        binding.root.toolbar_rl_setting.setOnClickListener { listener.onClickSetting() }
    }

    fun setText(title: String) {
        binding.root.toolbar_tv_title.text = title
    }

    fun showBack(isShow: Boolean) {
        if (isShow) binding.root.toolbar_iv_back.visible() else binding.root.toolbar_iv_back.gone()
    }

    fun showSearch(isShow: Boolean) {
        if (isShow) binding.root.toolbar_iv_search.visible() else binding.root.toolbar_iv_search.gone()
    }

    fun showCart(isShow: Boolean) {
        if (isShow) binding.root.toolbar_rl_cart.visible() else binding.root.toolbar_rl_cart.gone()
    }

    fun setCartNoti(count: Int) {
        if (count > 0) {
            binding.root.toolbar_tv_cart_noti.text = count.toString()
            binding.root.toolbar_tv_cart_noti.visible()
        } else {
            binding.root.toolbar_tv_cart_noti.gone()
        }
        showCart(count > 0)
    }

    fun showSetting(isShow: Boolean) {
        if (isShow) binding.root.toolbar_rl_setting.visible() else binding.root.toolbar_rl_setting.gone()
    }

    fun setSettingNoti(count: Int) {
        if (count > 0) {
            binding.root.toolbar_tv_setting_noti.text = count.toString()
            binding.root.toolbar_tv_setting_noti.visible()

        } else {
            binding.root.toolbar_tv_setting_noti.gone()
        }
        showSetting(count > 0)
    }

    interface OnCustomToolbarListener {
        fun onClickBack()
        fun onClickSearch()
        fun onClickCart()
        fun onClickSetting()
    }
}

