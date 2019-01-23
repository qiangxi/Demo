package com.qiangxi.demo.architecture.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qiangxi.demo.architecture.entity.User

/**
 * Create By renqiangqiang . 2019/1/23
 */
class UserViewModel : ViewModel() {

    fun getUser(): LiveData<User> {
        val u = User("zhangsan")
        val m = MutableLiveData<User>()
        m.value = u
        return m
    }

    override fun onCleared() {
        super.onCleared()
    }
}

class UserViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel() as T
    }
}