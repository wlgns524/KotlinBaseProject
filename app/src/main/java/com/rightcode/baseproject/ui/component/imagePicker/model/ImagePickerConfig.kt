package com.rightcode.baseproject.ui.component.imagePicker.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler


class ImagePickerConfig(
    var isCameraMode: Boolean = false,
    var isMultipleMode: Boolean = false,
    var isShowCamera: Boolean = false,
    var maxSize: Int
) : Parcelable {

    constructor() : this(
        isCameraMode = false,
        isMultipleMode = false,
        isShowCamera = false,
        maxSize = 0
    )

    constructor(parcel: Parcel) : this() {
        isCameraMode = parcel.readByte() != 0.toByte()
        isMultipleMode = parcel.readByte() != 0.toByte()
        isShowCamera = parcel.readByte() != 0.toByte()
        maxSize = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isCameraMode) 1 else 0)
        parcel.writeByte(if (isMultipleMode) 1 else 0)
        parcel.writeByte(if (isShowCamera) 1 else 0)
        parcel.writeInt(maxSize)
    }

    override fun describeContents() = 0

    companion object {
        const val EXTRA_CONFIG = "ImagePickerConfig"
        const val EXTRA_IMAGES = "ImagePickerImages"
        const val RC_PICK_IMAGES = 100
        const val RC_CAPTURE_IMAGE = 101
        const val RC_WRITE_EXTERNAL_STORAGE_PERMISSION = 102
        const val RC_CAMERA_PERMISSION = 103
        const val MAX_SIZE = Int.MAX_VALUE
        val ROOT_DIR_DCIM: String = android.os.Environment.DIRECTORY_DCIM
        val ROOT_DIR_DOWNLOAD: String = android.os.Environment.DIRECTORY_DOWNLOADS
        val ROOT_DIR_PICTURES: String = android.os.Environment.DIRECTORY_PICTURES

        @JvmField
        val CREATOR = object : Parcelable.Creator<ImagePickerConfig> {
            override fun createFromParcel(parcel: Parcel) = ImagePickerConfig(parcel)

            override fun newArray(size: Int) = arrayOfNulls<ImagePickerConfig>(size)
        }
    }
}