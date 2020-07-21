package com.exuberant.workindia.data.network.request

import com.google.gson.annotations.SerializedName

data class AgentRequest(
    @SerializedName("agent_id")
    val agentId: String,
    @SerializedName("agent_password")
    val agentPassword: String
)