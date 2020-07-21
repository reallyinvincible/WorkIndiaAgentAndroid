package com.exuberant.workindia.ui.home.bottomsheets

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.exuberant.workindia.R
import com.exuberant.workindia.data.model.Task
import com.exuberant.workindia.ui.home.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_modify_user.*
import kotlinx.android.synthetic.main.bottomsheet_modify_user.et_due_by
import kotlinx.android.synthetic.main.bottomsheet_modify_user.et_title
import kotlinx.android.synthetic.main.bottomsheet_modify_user.et_password
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ModifyUserBottomSheet(val task: Task) : BottomSheetDialogFragment() {

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
        return inflater.inflate(R.layout.bottomsheet_modify_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        btn_modify_task_option.setOnClickListener {
            ll_button_container.visibility = View.GONE
            ll_modify_user_layout.visibility = View.VISIBLE
        }
        btn_delete_task_option.setOnClickListener {
            //homeActivity.deleteUser(task.taskId)
            dismiss()
        }
        btn_modify_task.setOnClickListener {
            validateInputAndUpdateUser()
        }
        val simple: DateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z")
        val result = Date(task.dueBy)
        et_title.setText(task.title)
        et_password.setText(task.description)
        et_category.setText(task.category)
        et_due_by.setText(simple.format(result))
    }

    private fun validateInputAndUpdateUser() {
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
            //homeActivity.updateUser(
//                task.taskId,
//                CreateTaskRequest(title, description, category, dueBy)
//            )
            dismiss()
        }
    }

}