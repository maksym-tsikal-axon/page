package com.example.page.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.page.api.RandomUserApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RandomUserRepository @Inject constructor(private val randomUserApi: RandomUserApi) {

    fun getUserResults(page: Int) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {RandomUserPagingSource(randomUserApi, page)}
        ).liveData
}