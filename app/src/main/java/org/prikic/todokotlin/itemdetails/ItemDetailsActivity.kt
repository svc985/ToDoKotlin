package org.prikic.todokotlin.itemdetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_item_details.*
import org.prikic.todokotlin.R
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.util.Message
import timber.log.Timber

class ItemDetailsActivity : AppCompatActivity() {

    private var itemDetailsVM: ItemDetailsViewModel? = null

    private var taskToBeEdited: Task? = null

    companion object Factory{

        val BUNDLE_TASK = "task"

        fun start(ctx: Context, task: Task?) {
            val intent = Intent(ctx, ItemDetailsActivity::class.java)
            intent.putExtra(BUNDLE_TASK, task)
            ctx.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        Timber.d("Item details activity opened")

        taskToBeEdited = intent?.getParcelableExtra(BUNDLE_TASK)

        if (taskToBeEdited != null) {
            activity_item_details_button.text = getString(R.string.activity_item_details_edit)
            prepopulateUIWithTaskData(taskToBeEdited)
        }

        Timber.d("transferred task:%s", taskToBeEdited)
        
        itemDetailsVM = ViewModelProviders.of(this).get(ItemDetailsViewModel::class.java)

        activity_item_details_edit_text.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                activity_item_details_button.performClick()
                hideKeyboard(v)
                true
            } else {
                false
            }
        }

        activity_item_details_button.setOnClickListener { _ ->
            activity_item_details_edit_text.clearFocus()
            submit()
        }
    }

    private fun prepopulateUIWithTaskData(taskToBeEdited: Task?) {
        val weekDay = taskToBeEdited?.weekDay
        (0 until activity_item_details_spinner.count)
                .filter { activity_item_details_spinner.getItemAtPosition(it).toString() == weekDay }
                .forEach { activity_item_details_spinner.setSelection(it) }

        activity_item_details_edit_text.setText(taskToBeEdited?.taskText, TextView.BufferType.EDITABLE)
    }

    private fun hideKeyboard(v: View) {
        val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    private fun submit() {

        val isInputValid = validateInput()
        if (!isInputValid) return

        val weekDay = activity_item_details_spinner.selectedItem.toString()
        val taskText = activity_item_details_edit_text.text.toString()
        val task = Task(weekDay = weekDay, taskText = taskText)

        if (taskToBeEdited == null) {
            itemDetailsVM?.saveTask(task)?.observe(this, Observer {
                message -> run {
                Timber.d("message is:$message")
                when (message) {
                    Message.SUCCESS -> reloadScreen()
                    Message.ERROR -> Timber.e("There was an error while saving the task")
                    else -> {
                        Timber.e("Message has invalid key")
                    }
                }
            }
            })
        } else {
            if (taskToBeEdited == task) {
                Timber.d("Tasks are equal, do nothing")
                return
            } else {
                Timber.d("Updating task...")
                task.id = taskToBeEdited!!.id
                itemDetailsVM?.updateTask(task)
                reloadScreen()
            }
        }

    }

    private fun validateInput() : Boolean {
       if (activity_item_details_spinner.selectedItemPosition == 0) {
           Timber.e("First item can't be selected")
           return false
       }

        if (activity_item_details_edit_text.length() == 0) {
            Timber.e("Edit text can't be empty")
            return false
        }
        return true
    }

    private fun reloadScreen() {
        Timber.d("Reloading screen in progress")
        activity_item_details_spinner.setSelection(0)
        activity_item_details_edit_text.text.clear()
    }

}