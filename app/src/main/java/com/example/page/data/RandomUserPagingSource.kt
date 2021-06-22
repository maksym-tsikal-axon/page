package com.example.page.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.page.api.RandomUserApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class RandomUserPagingSource(
    private val randomUserApi: RandomUserApi,
    private val page: Int
) : PagingSource<Int, User>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = randomUserApi.getUsers(position)
            val users = response.results

            LoadResult.Page(
                data = users,
                prevKey = if(position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if(users.isEmpty()) null else position + 1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)
        } catch (exception: HttpException){
            LoadResult.Error(exception)
        }
    }

}