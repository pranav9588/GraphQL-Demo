package com.example.graphql

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface GraphQLService {

    @Headers("Content-Type: application/json")
    @POST("graphql")
    suspend fun getData(
        @Body body: String,
        @Header("Authorization") authorization: String
    ): Response<String>
}