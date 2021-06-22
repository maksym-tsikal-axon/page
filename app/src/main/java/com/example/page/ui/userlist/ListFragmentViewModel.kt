package com.example.page.ui.userlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.page.data.RandomUserRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class ListFragmentViewModel @ViewModelInject constructor(
    private val repository: RandomUserRepository
) : ViewModel() {

    private val currentPageNumber = MutableLiveData(DEFAULT_PAGE_NUM)

    val users = currentPageNumber.switchMap { pageInt ->
        repository.getUserResults(pageInt).cachedIn(viewModelScope)
    }

    fun getUsers(page: Int){
        currentPageNumber.value = page
    }

    companion object{
        private const val DEFAULT_PAGE_NUM = 1
    }
}