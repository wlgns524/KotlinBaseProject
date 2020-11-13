package com.rightcode.baseproject.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rightcode.baseproject.data.repository.LocalRepository
import com.rightcode.baseproject.model.local.User
import com.rightcode.baseproject.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//class MainViewModel @ViewModelInject constructor(private val postsRepository: PostsRepository) :
//@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(
    private val localRepository: LocalRepository
) :
    BaseViewModel() {

    private val _insertUser: MutableLiveData<Long> = MutableLiveData()
    val insertUser: LiveData<Long>
        get() = _insertUser

    init {
//        insertUser()
    }

    private fun insertUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val longValue = localRepository.insertUser(
                User(
                    firstName = "firstName1",
                    lastName = "lastName1"
                )
            )
            if (longValue > 0) {
                _insertUser.postValue(longValue)
            }
        }
    }

}
