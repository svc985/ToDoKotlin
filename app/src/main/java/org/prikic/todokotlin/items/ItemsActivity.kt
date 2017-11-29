package org.prikic.todokotlin.items

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_items.*
import kotlinx.android.synthetic.main.content_items.*
import org.prikic.todokotlin.R
import org.prikic.todokotlin.data.model.Task
import org.prikic.todokotlin.extensions.launchActivity
import org.prikic.todokotlin.itemdetails.ItemDetailsActivity
import timber.log.Timber
import java.util.*

class ItemsActivity : AppCompatActivity() {

    private lateinit var itemsVM: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        setSupportActionBar(toolbar)

        val adapter = TaskAdapter(LinkedList(), this)
        initRecyclerView(adapter)

        itemsVM = ViewModelProviders.of(this).get(ItemsViewModel::class.java)
        itemsVM.getTasks().observe(this, Observer<List<Task>> { tasks ->
            Timber.d("tasks size:${tasks?.size}")
            adapter.addItems(tasks?.toMutableList())
        })

        fab.setOnClickListener { _ ->
            Timber.d("open Item Details screen")
            launchActivity<ItemDetailsActivity> {  }

        }
    }

    private fun initRecyclerView(adapter: TaskAdapter) {
        val layoutManager = LinearLayoutManager(this)
        tasks_recycler_view.layoutManager = layoutManager
        tasks_recycler_view.itemAnimator = DefaultItemAnimator()
        tasks_recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            when (item.itemId) {
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }
}
