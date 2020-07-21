package com.exuberant.workindia.data.network.request

import com.google.gson.annotations.SerializedName

data class CreateTaskRequest(
    val title: String,
    val description: String,
    val category: String,
    @SerializedName("due_by")
    val dueBy: Long
)