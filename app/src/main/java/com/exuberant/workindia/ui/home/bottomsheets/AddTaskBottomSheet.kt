package com.exuberant.workindia.ui.home.bottomsheets

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.exuberant.workindia.R
import com.exuberant.workindia.ui.home.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottomsheet_add_task.*

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
        btn_become_agent.setOnClickListener {
            validateInputAndCreateUser()
        }
    }

    private fun validateInputAndCreateUser() {
        val name = et_title.text.toString()
        val regNo = et_password.text.toString()
        var cgpaString = et_due_by.text.toString()
        val cgpa = cgpaString.toFloatOrNull()
        if (name.isNullOrEmpty()) {
            val toast =
                Toast.makeText(homeActivity, "Name cannot be left empty", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else if (regNo.isNullOrEmpty()) {
            val toast =
                Toast.makeText(
                    homeActivity,
                    "Registration Number cannot be left empty",
                    Toast.LENGTH_SHORT
                )
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else if (cgpaString.isNullOrEmpty()) {
            val toast =
                Toast.makeText(homeActivity, "CGPA cannot be left empty", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        } else {
            //homeActivity.createUser(name, regNo, cgpa!!)
            dismiss()
        }
    }

}