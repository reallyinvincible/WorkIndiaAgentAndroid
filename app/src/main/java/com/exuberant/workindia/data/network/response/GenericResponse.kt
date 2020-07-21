package com.exuberant.workindia.data.network.response

class GenericResponse(
    val result: String,
    val message: String,
    val error: Boolean,
    val payload: Any
)