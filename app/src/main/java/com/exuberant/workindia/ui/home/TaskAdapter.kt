package com.exuberant.workindia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exuberant.workindia.R
import com.exuberant.workindia.data.model.Task
import com.exuberant.workindia.ui.home.bottomsheets.ModifyUserBottomSheet
import kotlinx.android.synthetic.main.list_item_task.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class TaskAdapter(private val taskList: List<Task>, val homeActivity: HomeActivity) :
    RecyclerView.Adapter<TaskAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_task, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindUser(taskList[position])
        holder.itemView.setOnLongClickListener {
            val bottomSheet: ModifyUserBottomSheet = ModifyUserBottomSheet(taskList[position])
            bottomSheet.show(homeActivity.supportFragmentManager, "")
            return@setOnLongClickListener true
        }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView = itemView.tv_category
        val titleTextView = itemView.tv_title
        val descriptionTextView = itemView.tv_description
        val dueDateTextView = itemView.tv_due_by

        fun bindUser(task: Task) {
            val simple: DateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z")
            val result = Date(task.dueBy)
            categoryTextView.text = "#${task.category}"
            titleTextView.text = task.title
            descriptionTextView.text = task.description
            dueDateTextView.text = simple.format(result)
        }

    }

}