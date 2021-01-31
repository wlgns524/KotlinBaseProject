package com.rightcode.baseproject.ui.component.imagePicker.listener

import com.rightcode.baseproject.ui.component.imagePicker.model.Image

interface OnImageSelectListener {
    fun onSelectedImage(position: Int, image: Image)
    fun onSelectedImageDone()
}