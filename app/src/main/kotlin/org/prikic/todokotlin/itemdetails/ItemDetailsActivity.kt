package org.prikic.todokotlin.itemdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.prikic.todokotlin.R
import timber.log.Timber

class ItemDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_details)

        Timber.d("Item details activity opened")
    }
}