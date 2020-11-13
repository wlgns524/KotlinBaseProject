package com.rightcode.baseproject.ui.main.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.rightcode.baseProject.ui.BaseFragment
import com.rightcode.baseproject.R
import com.rightcode.baseproject.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : BaseFragment<ListViewModel, FragmentListBinding>() {

    override val mViewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding.lifecycleOwner = this

        return mViewDataBinding.root
    }


    override fun getLayoutRes(): Int {
        return R.layout.fragment_list
    }


    override fun getDataViewBinding(): FragmentListBinding =
        FragmentListBinding.inflate(layoutInflater)

}