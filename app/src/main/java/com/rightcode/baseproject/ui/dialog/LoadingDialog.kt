package com.rightcode.baseproject.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.DialogLoadingBinding


class LoadingDialog(context: Context, cancel: Boolean) :
    Dialog(context, R.style.Dialog_AppTheme_Transparent) {

    init {
        val binding = DataBindingUtil.inflate<DialogLoadingBinding>(
            LayoutInflater.from(context), R.layout.dialog_loading, null, false
        )
        binding.dialogBackground.setBackgroundResource(R.color.color_00000000)
        binding.progress.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(
                context,
                R.color.color_000000
            ), PorterDuff.Mode.SRC_ATOP
        )
        if (cancel) {
            binding.dialogBackground.setOnClickListener { dismiss() }
        } else {
            binding.dialogBackground.setOnClickListener(null)
        }
    }
}


