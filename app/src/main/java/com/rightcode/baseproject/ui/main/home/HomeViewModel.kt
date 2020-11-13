package com.rightcode.baseproject.ui.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rightcode.baseproject.data.repository.LocalRepository
import com.rightcode.baseproject.model.local.User
import com.rightcode.baseproject.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel @ViewModelInject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _getUsers: MutableLiveData<List<User>> = MutableLiveData()
    val getUser: LiveData<List<User>>
        get() = _getUsers

    init {
//        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val users = localRepository.getAll()
            if (users.size > 0) {
                _getUsers.postValue(users)
            }
        }
    }
}
