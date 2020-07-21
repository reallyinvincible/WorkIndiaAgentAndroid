package com.exuberant.workindia.data.network.response

import com.exuberant.workindia.data.model.Task

data class GetTasksResponse(
    val result: String,
    val message: String,
    val error: Boolean,
    val payload: List<Task>
)