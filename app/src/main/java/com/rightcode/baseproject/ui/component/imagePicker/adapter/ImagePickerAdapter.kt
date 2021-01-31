package com.rightcode.baseproject.ui.component.imagePicker.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.ItemImagePickerBinding
import com.rightcode.baseproject.ui.component.imagePicker.ImagePickerViewModel
import com.rightcode.baseproject.ui.component.imagePicker.model.Image
import com.rightcode.baseproject.util.extension.gone
import com.rightcode.baseproject.util.extension.visible


class ImagePickerAdapter(val viewModel: ImagePickerViewModel) :
    RecyclerView.Adapter<ImagePickerAdapter.ImagePickerHolder?>() {

    var images: ArrayList<Image> = ArrayList()
//    var selectedImages = arrayListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePickerHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_image_picker, parent, false)
        val height: Int = parent.measuredHeight / 3
        itemView.minimumHeight = height

        return ImagePickerHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ImagePickerHolder, position: Int) {
        viewHolder.binding.image = images[position]
        viewHolder.binding.position = position
        viewHolder.binding.viewModel = viewModel
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ImagePickerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemImagePickerBinding.bind(itemView)
    }

    override fun getItemId(position: Int): Long = position.toLong()
}

@BindingAdapter("android:src")
fun loadUri(imageView: ImageView, uri: Uri) {
    Glide.with(imageView.context)
        .load(uri)
        .apply {
            RequestOptions()
        }
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    if (value) view.visible() else view.gone()
}