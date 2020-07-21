package com.exuberant.workindia.data.network.api

import android.content.Context
import com.exuberant.workindia.data.network.request.CreateTaskRequest
import com.exuberant.workindia.data.network.response.GetTaskResponse
import com.exuberant.workindia.data.network.response.GetTasksResponse
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface TaskApi {

    @POST("/app/sites/")
    fun createTask(
        @Query("agent") agentId: String,
        @Body createTaskRequest: CreateTaskRequest
    ): Observable<GetTaskResponse>


    @GET("app/sites/list/")
    fun getTask(
        @Query("agent") agentId: String
    ): Observable<GetTasksResponse>

    companion object {
        operator fun invoke(context: Context): TaskApi {
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.addInterceptor(ChuckInterceptor(context))
            return Retrofit.Builder()
                .baseUrl("https://agent-todo.herokuapp.com/")
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TaskApi::class.java)
        }
    }

}