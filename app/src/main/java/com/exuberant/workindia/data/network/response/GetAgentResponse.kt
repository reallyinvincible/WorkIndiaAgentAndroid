package com.exuberant.workindia.data.network.response

import com.exuberant.workindia.data.model.Agent

data class GetAgentResponse(
    val result: String,
    val message: String,
    val error: Boolean,
    val payload: Agent
)