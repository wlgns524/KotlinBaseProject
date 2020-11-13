package com.rightcode.baseproject.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater.from
import android.view.View
import androidx.databinding.DataBindingUtil
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.DialogCommonBinding
import com.rightcode.baseproject.util.extension.gone
import com.rightcode.baseproject.util.extension.visible
import kotlinx.android.synthetic.main.dialog_common.view.*

class CommonDialog(context: Context) : Dialog(context, R.style.Dialog_AppTheme_Transparent) {

    var binding: DialogCommonBinding =
        DataBindingUtil.inflate(from(context), R.layout.dialog_common, null, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun setTile(res: Int) {
        setTitle(context.getString(res))
    }

    override fun setTitle(title: CharSequence?) {
        setTitle(title)
    }

    fun setTitle(title: String?) {
        if (title.isNullOrEmpty()) {
            binding.tvTitle.gone()
        } else {
            binding.tvTitle.visible()
            binding.tvTitle.text = title
        }
    }

    fun setMessage(message: CharSequence?) {
        setMessage(message.toString())
    }

    fun setMessage(res: Int) {
        setMessage(context.getString(res))
    }

    fun setMessage(message: String) {
        if (message.isNullOrEmpty()) {
            binding.tvMessage.gone()
            binding.tvMessage.text = context.getString(R.string.common_error)
        } else {
            binding.tvMessage.visible()
            binding.tvMessage.text = message
        }
    }

    fun setPositiveButton(text: String) {
        setPositiveButton(text, null)
    }

    fun setPositiveButton(res: Int, onClick: (() -> Unit)?) {
        setPositiveButton(context.getString(res), onClick)
    }

    fun setPositiveButton(text: String, onClick: (() -> Unit)?) {
        binding.tvPositive.visible()
        binding.tvPositive.text = text
        if (onClick != null)
            binding.tvPositive.setOnClickListener { onClick }
        else
            binding.tvPositive.setOnClickListener { (dismiss()) }
    }

    fun setNegativeButton(text: String, listener: View.OnClickListener?) {
        binding.tvNegative.visible()
        binding.viewLine.visible()
        binding.tvNegative.text = text
        binding.tvNegative.setOnClickListener { listener }
    }
}