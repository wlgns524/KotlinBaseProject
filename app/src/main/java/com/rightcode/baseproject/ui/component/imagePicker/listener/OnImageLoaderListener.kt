package com.rightcode.baseproject.ui.component.imagePicker.listener

import com.rightcode.baseproject.ui.component.imagePicker.model.Image

interface OnImageLoaderListener {
    fun onImageLoaded(images: ArrayList<Image>)
    fun onFailed(throwable: Throwable)
}