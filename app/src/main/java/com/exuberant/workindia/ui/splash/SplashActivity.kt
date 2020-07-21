package com.exuberant.workindia.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.exuberant.workindia.R
import com.exuberant.workindia.base.BaseActivity
import com.exuberant.workindia.data.network.api.TaskApi
import com.exuberant.workindia.ui.auth.AuthActivity
import com.exuberant.workindia.ui.home.HomeActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashActivity : BaseActivity() {

    private var hasSplashShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initialize()
    }

    @SuppressLint("CheckResult")
    private fun initialize() {
        //Empty call to start Heroku Server
        TaskApi.invoke(this)
            .getTask("sparsh")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                launchAuth()
                hasSplashShown = true
            }, {
                launchAuth()
                hasSplashShown = true
            })

    }

    override fun onResume() {
        super.onResume()
        if (hasSplashShown) {
            launchAuth()
        }
    }

    private fun launchAuth() {
        val intent = Intent(this@SplashActivity, AuthActivity::class.java)
        startActivity(intent)
        finishAfterTransition()
    }

}