package com.pranav.graphql.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ServiceLoader
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DependencyInjection {
    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        val client = OkHttpClient
            .Builder().addInterceptor(Interceptor { chain ->
                val request: Request =
                    chain.request().newBuilder()
                        .build()
                chain.proceed(request)
            })
        val builder = GsonBuilder()
        for (factory in ServiceLoader.load(
            TypeAdapterFactory::class.java
        )) {
            builder.registerTypeAdapterFactory(factory)
        }
        val gson = builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss2").setLenient().create()
        return Retrofit.Builder().client(client.build())
            .baseUrl("https://countries.trevorblades.com/graphql")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Singleton
    @Provides
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}
