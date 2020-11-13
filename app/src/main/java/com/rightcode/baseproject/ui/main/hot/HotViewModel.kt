package com.rightcode.baseproject.ui.main.hot

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rightcode.baseproject.data.remote.RemoteRepository
import com.rightcode.baseproject.model.State
import com.rightcode.baseproject.model.remote.TryList
import com.rightcode.baseproject.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class HotViewModel @ViewModelInject constructor(
    private val remoteRepository: RemoteRepository
) :
    BaseViewModel() {

    private var _test = MutableLiveData<State<TryList>>()
    val test: LiveData<State<TryList>>
        get() = _test

    init {
        getTest()
    }

    private fun getTest() {
        viewModelScope.launch {
            remoteRepository.getTest().collect {
                _test.value = it
            }
        }
    }
}
