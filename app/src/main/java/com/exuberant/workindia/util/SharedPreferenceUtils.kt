package com.exuberant.workindia.util

import android.content.Context
import android.content.SharedPreferences
import com.exuberant.workindia.data.model.Agent
import com.google.gson.Gson

class SharedPreferenceUtils(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AgentTodo", Context.MODE_PRIVATE)
    private var gson: Gson = Gson()

    companion object {
        const val SHARED_PREFERENCE_USER_CONSTANT = "agent"
    }

    fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    fun saveUser(agentModel: Agent) {
        val userString = gson.toJson(agentModel)
        getEditor().putString(SHARED_PREFERENCE_USER_CONSTANT, userString).apply()
    }

    fun getUser(): Agent? {
        return gson.fromJson(
            sharedPreferences.getString(SHARED_PREFERENCE_USER_CONSTANT, null),
            Agent::class.java
        )
    }


}