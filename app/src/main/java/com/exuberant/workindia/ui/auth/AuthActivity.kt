package com.exuberant.workindia.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exuberant.workindia.R
import com.exuberant.workindia.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initialize()
    }

    private fun initialize() {
        btn_become_agent.setOnClickListener {
            val bottomSheet = NewAgentBottomSheet()
            bottomSheet.show(supportFragmentManager, "")
        }
        btn_enter_lair.setOnClickListener {
            val intent = Intent(this@AuthActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}