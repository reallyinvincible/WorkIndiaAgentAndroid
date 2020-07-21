package com.exuberant.workindia.data.model

import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("task_id")
    val taskId: Int,
    val title: String,
    val description: String,
    val category: String,
    @SerializedName("due_by")
    val dueBy: Long
)