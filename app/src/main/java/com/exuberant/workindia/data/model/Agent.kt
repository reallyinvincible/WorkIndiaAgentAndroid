package com.exuberant.workindia.data.model

import com.google.gson.annotations.SerializedName

data class Agent(
    @SerializedName("agent_id")
    val agentId: String
)