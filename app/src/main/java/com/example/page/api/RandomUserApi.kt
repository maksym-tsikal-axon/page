package com.example.page.api

import retrofit2.http.GET
import retrofit2.http.Query


interface RandomUserApi {

    companion object{
        const val BASE_URL = "https://randomuser.me/api/"
    }

    @GET("?page=1&results=10&seed=abc")
    suspend fun getUsers(@Query ("page") page: Int) : RandomUserResponse
    
}