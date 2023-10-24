package com.example.dailynewsapp.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchesService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()

    val restMatchesApi = retrofit.create(RestMatchesApi::class.java)

    private fun getOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                        .url(chain.request().url)
                    builder.header("X-RapidAPI-Key","e3d62a9dfcmshdac9ce328041b01p1bb822jsna80bc1e426f9"                    )
                    builder.header("X-RapidAPI-Host", "sports-data-api.p.rapidapi.com")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
            addNetworkInterceptor(logger)
        }.build()
        return okHttpClient
    }

    companion object {
        const val BASE_URL: String = "https://sports-data-api.p.rapidapi.com/api/v1/sports/"
    }
}

