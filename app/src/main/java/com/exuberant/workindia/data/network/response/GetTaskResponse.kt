package com.exuberant.workindia.data.network.response

import com.exuberant.workindia.data.model.Task

data class GetTaskResponse(
    val result: String,
    val message: String,
    val error: Boolean,
    val payload: Task
)