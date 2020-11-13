package com.rightcode.baseproject.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rightcode.baseProject.ui.BaseFragment
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val mViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding.lifecycleOwner = this
        mViewDataBinding.viewModel = mViewModel
        mViewModel.getUser.observe(getViewLifecycleOwner(), Observer {
            it.forEach {
                println("User : $it")
            }
        })
        return mViewDataBinding.root
    }


    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun getDataViewBinding(): FragmentHomeBinding =
        FragmentHomeBinding.inflate(layoutInflater)
}