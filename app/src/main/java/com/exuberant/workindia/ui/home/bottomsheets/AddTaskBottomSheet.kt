package com.exuberant.workindia.ui.home.bottomsheets

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.exuberant.workindia.R
import com.exuberant.workindia.data.network.request.CreateTaskRequest
import com.exuberant.workindia.ui.home.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_add_task.*
import kotlinx.android.synthetic.main.bottomsheet_add_task.et_category
import kotlinx.android.synthetic.main.bottomsheet_add_task.et_due_by
import kotlinx.android.synthetic.main.bottomsheet_add_task.et_password
import kotlinx.android.synthetic.main.bottomsheet_add_task.et_title
import kotlinx.android.synthetic.main.bottomsheet_modify_user.*

class AddTaskBottomSheet : BottomSheetDialogFragment() {

    private lateinit var homeActivity: HomeActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeActivity = context as HomeActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        btn_add_task.setOnClickListener {
            validateInputAndCreateTask()
        }
    }

    private fun validateInputAndCreateTask() {
        val title = et_title.text.toString()
        val description = et_password.text.toString()
        val category = et_category.text.toString()
        var dueByString = et_due_by.text.toString()
        val dueBy = dueByString.toLong()
        if (title.isNullOrEmpty()) {
            val toast =
                Toast.makeText(homeActivity, "Title cannot be left empty", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else if (description.isNullOrEmpty()) {
            val toast =
                Toast.makeText(
                    homeActivity,
                    "Description cannot be left empty",
                    Toast.LENGTH_SHORT
                )
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else if (dueByString.isNullOrEmpty()) {
            val toast =
                Toast.makeText(homeActivity, "Due date cannot be left empty", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else if (category.isNullOrEmpty()) {
            val toast =
                Toast.makeText(homeActivity, "Category cannot be left empty", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else {
            homeActivity.createTask(
                CreateTaskRequest(title, description, category, dueBy)
            )
            dismiss()
        }
    }

}