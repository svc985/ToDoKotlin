package org.prikic.todokotlin.items

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.task_card.view.*
import org.prikic.todokotlin.R
import org.prikic.todokotlin.data.model.Task

class TaskAdapter(private var tasks: MutableList<Task>?): RecyclerView.Adapter<TaskAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.task_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks?.get(position)
        holder.day.text = task?.weekDay
        holder.description.text = task?.taskText
    }

    override fun getItemCount(): Int = if(tasks == null) 0 else tasks!!.size

    fun addItems(tasks: MutableList<Task>?) {
        this.tasks?.clear()
        this.tasks = tasks
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val day: TextView = view.day
        val description: TextView = view.description
    }
}