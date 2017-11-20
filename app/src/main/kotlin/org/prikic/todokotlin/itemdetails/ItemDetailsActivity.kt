package org.prikic.todokotlin.itemdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_item_details.*
import org.prikic.todokotlin.R
import timber.log.Timber

class ItemDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        Timber.d("Item details activity opened")

        activity_item_details_button.setOnClickListener { _ ->
            Timber.d("clicked save/edit button")
        }
    }
}