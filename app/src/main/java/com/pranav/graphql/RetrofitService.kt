package com.pranav.graphql

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {
    @Headers("Content-Type: application/json")
    @POST("https://countries.trevorblades.com/graphql")
    suspend fun getData(@Body body: JsonObject)


    companion object {
        private var retrofitService: RetrofitService? = null
        private var gson = GsonBuilder()
            .setLenient()
            .create()

        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private var client: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .build()


        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                retrofitService = Retrofit.Builder()
                    .baseUrl("https://countries.trevorblades.com/graphql")
                    .addConverterFactory(DataConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build().create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}