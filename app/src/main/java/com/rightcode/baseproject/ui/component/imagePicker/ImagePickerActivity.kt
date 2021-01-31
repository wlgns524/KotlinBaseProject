package com.rightcode.baseproject.ui.component.imagePicker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.rightcode.basekProject.ui.BaseActivity
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.ActivityImagePickerBinding
import com.rightcode.baseproject.ui.component.imagePicker.adapter.ImagePickerAdapter
import com.rightcode.baseproject.ui.component.imagePicker.helper.GridSpacingItemDecoration
import com.rightcode.baseproject.ui.component.imagePicker.listener.OnImageSelectListener
import com.rightcode.baseproject.ui.component.imagePicker.model.Image
import com.rightcode.baseproject.ui.component.imagePicker.model.ImagePickerConfig
import com.rightcode.baseproject.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagePickerActivity : BaseActivity<ImagePickerViewModel, ActivityImagePickerBinding>(),
    OnImageSelectListener {

    override val mViewModel: ImagePickerViewModel by viewModels()
    lateinit var imageAdapter: ImagePickerAdapter
    lateinit var config: ImagePickerConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewDataBinding.root)

        initialize()
    }

    override fun initialize() {
        super.initialize()
        config = intent?.getParcelableExtra(ImagePickerConfig.EXTRA_CONFIG)!!

        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.viewModel = mViewModel
        imageAdapter = ImagePickerAdapter(mViewModel)
        imageAdapter.setHasStableIds(true)

        intent.getParcelableArrayListExtra<Image>("selectImage")?.let {
            mViewModel.setSelectImages(it)
        }

        mViewModel.imageload.observe(this, Observer { images ->
            imageAdapter.images = images
            imageAdapter.notifyDataSetChanged()
        })
        mViewModel.onImageSelectListener = this

        // RecylcerView Initialize
        with(mViewDataBinding.rvImage)
        {
            layoutManager = GridLayoutManager(applicationContext, 3)
            setHasFixedSize(true)
            addItemDecoration(
                GridSpacingItemDecoration(
                    3,
                    3,
                    false
                )
            )
            adapter = imageAdapter
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_image_picker
    }

    override fun getDataViewBinding(): ActivityImagePickerBinding =
        ActivityImagePickerBinding.inflate(layoutInflater)

    override fun onSelectedImage(position: Int, image: Image) {
        if (!image.selected && imageAdapter.images.filter { image1 -> image1.selected }.size >= config.maxSize) {
            toast("${config.maxSize}장까지만 선택할 수 있습니다")
            return
        }
        image.selected = !image.selected
        imageAdapter.images[position] = image
        imageAdapter.notifyItemChanged(position)
    }

    override fun onSelectedImageDone() {
        val images = imageAdapter.images.filter { image -> image.selected } as ArrayList<Image>

        val data = Intent()
        data.putParcelableArrayListExtra(ImagePickerConfig.EXTRA_IMAGES, images)
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
