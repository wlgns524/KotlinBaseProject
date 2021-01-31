package com.rightcode.baseproject.ui.component.imagePicker

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rightcode.baseproject.ui.base.BaseViewModel
import com.rightcode.baseproject.ui.component.imagePicker.listener.OnImageLoaderListener
import com.rightcode.baseproject.ui.component.imagePicker.listener.OnImageSelectListener
import com.rightcode.baseproject.ui.component.imagePicker.model.Image


class ImagePickerViewModel @ViewModelInject constructor(
    application: Application
) : BaseViewModel() {

    private val imageFileLoader: ImageFileLoader = ImageFileLoader(application.baseContext)
    private val _imageLoad: MutableLiveData<ArrayList<Image>> = MutableLiveData()
    val imageload: LiveData<ArrayList<Image>>
        get() = _imageLoad

    private var _selectImages: MutableLiveData<ArrayList<Image>> = MutableLiveData()
    lateinit var onImageSelectListener: OnImageSelectListener

    init {
        fetchImages()
    }

    fun fetchImages() {
        imageFileLoader.abortLoadImages()
        imageFileLoader.loadDeviceImages(object : OnImageLoaderListener {
            override fun onImageLoaded(images: ArrayList<Image>) {
                if (!_selectImages.value.isNullOrEmpty()) {
                    _selectImages.value?.forEach { image ->
                        val index = images.indexOfFirst {
                            it.uri == image.uri
                        }
                        images[index] = image
                    }
                }
                _imageLoad.postValue(images)
            }

            override fun onFailed(throwable: Throwable) {
                _imageLoad.postValue(arrayListOf())
            }
        })
    }

    fun setSelectImages(images: ArrayList<Image>) {
        _selectImages.postValue(images)
    }

    fun setSelectImage(position: Int, image: Image) {
        onImageSelectListener.onSelectedImage(position, image)
    }

    fun onSelectedImageDone() {
        onImageSelectListener.onSelectedImageDone()
    }
}
