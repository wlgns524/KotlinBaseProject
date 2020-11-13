package com.rightcode.baseproject.ui.main.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rightcode.baseproject.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
class ListViewModel @ViewModelInject constructor() :
    BaseViewModel() {

    private var _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    init {
        getData()
    }

    private fun getData() {
        _data.value = "Data is ListViewModel"
    }
}
