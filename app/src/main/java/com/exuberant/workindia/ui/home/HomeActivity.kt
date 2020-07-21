package com.exuberant.workindia.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import com.exuberant.workindia.R
import com.exuberant.workindia.base.BaseActivity
import com.exuberant.workindia.data.network.api.TaskApi
import com.exuberant.workindia.data.network.request.CreateTaskRequest
import com.exuberant.workindia.ui.home.bottomsheets.AddTaskBottomSheet
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HomeActivity : BaseActivity() {

    private lateinit var taskApi: TaskApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        taskApi = TaskApi.invoke(this)
        initialize()
    }

    private fun initialize() {
        loadTasks()
        btn_become_agent.setOnClickListener {
            val bottomSheet: AddTaskBottomSheet = AddTaskBottomSheet()
            bottomSheet.show(supportFragmentManager, "")
        }
        showModifyHintSnackbar()
    }

    private fun showModifyHintSnackbar() {
        val snackbar = Snackbar.make(
            findViewById(R.id.main_layout),
            "Long press on user cards to modify or delete",
            Snackbar.LENGTH_INDEFINITE
        )
        snackbar.setAction("Okay") { snackbar.dismiss() }
        snackbar.setActionTextColor(resources.getColor(R.color.colorPrimaryDark))
        snackbar.view.setBackgroundResource(R.color.colorSuccessSnackbar)
        snackbar.show()
    }

    private fun showSnackBar(message: String?, color: Int) {
        val snackbar = Snackbar.make(
            findViewById(R.id.main_layout),
            message!!,
            Snackbar.LENGTH_LONG
        )
        if (color == 0) {
            snackbar.view.setBackgroundResource(R.color.colorErrorSnackbar)
        } else {
            snackbar.view.setBackgroundResource(R.color.colorSuccessSnackbar)
        }
        snackbar.show()
    }

    @SuppressLint("CheckResult")
    private fun loadTasks() {
        showProgressDialog()
        taskApi.getTask("sparsh")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.payload.isNullOrEmpty()) {
                    showSnackBar("No data found. Please add data using add button.", 0)
                } else {
                    val adapter = TaskAdapter(it.payload, this)
                    rv_task_list.adapter = adapter
                }
                hideProgressDialog()
            }, {
                hideProgressDialog()
                EventBus.getDefault().post(it)
            })
    }

   /* @SuppressLint("CheckResult")
    fun createTask() {
        showProgressDialog()
        taskApi.createTask("sparsh", CreateTaskRequest(title, regNo, cgpa))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.error) {
                    EventBus.getDefault().post(it.message)
                }
                hideProgressDialog()
                loadUsers()
            }, {
                hideProgressDialog()
                EventBus.getDefault().post(it)
            })
    }*/

   /* @SuppressLint("CheckResult")
    fun updateUser(
        userId: Int,
        createTaskRequest: CreateTaskRequest
    ) {
        showProgressDialog()
        taskApi.updateUser(userId, createTaskRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.error) {
                    EventBus.getDefault().post(it.message)
                }
                hideProgressDialog()
                loadUsers()
            }, {
                hideProgressDialog()
                EventBus.getDefault().post(it)
            })
    }

    @SuppressLint("CheckResult")
    fun deleteUser(userId: Int) {
        showProgressDialog()
        taskApi.deleteUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.error) {
                    EventBus.getDefault().post(it.message)
                }
                hideProgressDialog()
                loadUsers()
            }, {
                hideProgressDialog()
                EventBus.getDefault().post(it)
            })
    }*/

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onError(throwable: Throwable) {
        showSnackBar(throwable.message, 0)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onError(error: String) {
        showSnackBar(error, 0)
    }

}