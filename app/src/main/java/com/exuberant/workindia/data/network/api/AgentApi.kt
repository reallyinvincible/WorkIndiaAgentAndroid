package com.exuberant.workindia.data.network.api

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface AgentApi {

    companion object {
        operator fun invoke(context: Context): AgentApi {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.addInterceptor(ChuckInterceptor(context))
            return Retrofit.Builder()
                .baseUrl("https://agent-todo.herokuapp.com/")
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AgentApi::class.java)
        }
    }

}