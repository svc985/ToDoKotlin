package org.prikic.todokotlin.items

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.task_card.view.*
import org.prikic.todokotlin.R
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.itemdetails.ItemDetailsActivity
import org.prikic.todokotlin.util.Message
import timber.log.Timber

class TaskAdapter(private var tasks: MutableList<Task>?, private val activity: FragmentActivity) :
        RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.task_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks?.get(position)
        holder.day.text = task?.weekDay
        holder.description.text = task?.taskText

        val itemsVM: ItemsViewModel = ViewModelProviders
                .of(activity)
                .get(ItemsViewModel::class.java)

        holder.container.setOnLongClickListener({ _: View ->

            itemsVM.deleteTask(task).observe(activity, Observer { message ->
                run {
                    Timber.d("message is:$message")
                    when (message) {
                        Message.SUCCESS -> Timber.d("Task deleted")
                        Message.ERROR -> Timber.e("Task deletion error")
                        else -> {
                            Timber.e("Message has invalid key")
                        }
                    }
                }
            })
            true
        })
        holder.container.setOnClickListener({
            Timber.d("update this task:$task")
            ItemDetailsActivity.start(activity)
        })
    }

    override fun getItemCount(): Int = if (tasks == null) 0 else tasks!!.size

    fun addItems(tasks: MutableList<Task>?) {
        this.tasks?.clear()
        this.tasks = tasks
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val day: TextView = view.day
        val description: TextView = view.description
        val container: CardView = view.card_view
    }
}