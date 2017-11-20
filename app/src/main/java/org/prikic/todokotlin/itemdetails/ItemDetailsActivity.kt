package org.prikic.todokotlin.itemdetails

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_item_details.*
import org.prikic.todokotlin.R
import timber.log.Timber

class ItemDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        Timber.d("Item details activity opened")

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
        Timber.d("clicked save/edit button")
    }
}