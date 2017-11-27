package org.prikic.todokotlin.itemdetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_item_details.*
import org.prikic.todokotlin.R
import org.prikic.todokotlin.data.model.Task
import timber.log.Timber

class ItemDetailsActivity : AppCompatActivity() {

    private var itemDetailsVM: ItemDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        Timber.d("Item details activity opened")

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
    
    private fun hideKeyboard(v: View) {
        val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    private fun submit() {
        //Timber.d("clicked save/edit button")

        val isInputValid = validateInput()
        if (!isInputValid) return

        val weekDay = activity_item_details_spinner.selectedItem.toString()
        val taskText = activity_item_details_edit_text.text.toString()
        val task = Task(weekDay = weekDay, taskText = taskText)
        itemDetailsVM?.saveToDo(task)?.observe(this, Observer {
            message -> Timber.d("message is:$message")
        })

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


}