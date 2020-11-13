package com.rightcode.baseproject.ui.main.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rightcode.baseProject.ui.BaseFragment
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.FragmentHotBinding
import com.rightcode.baseproject.model.State
import com.rightcode.baseproject.util.extension.LogD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HotFragment : BaseFragment<HotViewModel, FragmentHotBinding>() {

    override val mViewModel: HotViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding.lifecycleOwner = this
        mViewModel.test.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> showLoading(true)
                is State.Success -> {
                    state.data.menus.forEach {
                        LogD("34 User : ${it}")
                        LogD("34 User : ${it.img_url}")
                    }
                    hideLoading()
                }
                is State.Error -> {
                    println("38 User : ${state.message}")
                    println("39 User : ${state}")
                    Toast.makeText(context, "[${state}]", Toast.LENGTH_SHORT).show()
                    hideLoading()
                }
            }
        })

        return mViewDataBinding.root
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_hot
    }


    override fun getDataViewBinding(): FragmentHotBinding =
        FragmentHotBinding.inflate(layoutInflater)

}