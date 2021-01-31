package com.rightcode.baseproject.ui.main.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rightcode.baseProject.ui.BaseFragment
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.FragmentHomeBinding
import com.rightcode.baseproject.ui.component.imagePicker.ImagePickerActivity
import com.rightcode.baseproject.ui.component.imagePicker.model.Image
import com.rightcode.baseproject.ui.component.imagePicker.model.ImagePickerConfig
import com.rightcode.baseproject.util.extension.LogD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), ActionInterface {

    override val mViewModel: HomeViewModel by viewModels()
    var selectImage: ArrayList<Image> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.viewModel = mViewModel
        mViewModel.getUser.observe(viewLifecycleOwner, Observer {
            it.forEach {
                println("User : $it")
            }
        })
        mViewModel.actionInterface = this


        return mViewDataBinding.root
    }


    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun getDataViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)


    override fun goToGallery() {
        val intent = Intent(context, ImagePickerActivity::class.java)
        intent.putParcelableArrayListExtra("selectImage", selectImage)
        LogD(selectImage)
        intent.putExtra(
            ImagePickerConfig.EXTRA_CONFIG, ImagePickerConfig(
                isCameraMode = true,
                isMultipleMode = true,
                isShowCamera = true,
                maxSize = 3
            )
        )
        startActivityForResult(intent, 9999)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        LogD("onActivityResult")
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                9999 -> {
                    data?.getParcelableArrayListExtra<Image>(ImagePickerConfig.EXTRA_IMAGES)?.let {
                        selectImage = it
                        LogD(selectImage)
                    }
                }
            }
        }
    }
}