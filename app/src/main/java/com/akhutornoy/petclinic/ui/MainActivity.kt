package com.akhutornoy.petclinic.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem
import androidx.fragment.app.commit
import com.akhutornoy.petclinic.R
import com.akhutornoy.petclinic.entity.ui.HostModel
import com.akhutornoy.petclinic.ui.extension.showToast
import com.akhutornoy.petclinic.ui.host.HostsFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), HostsFragment.OnHostListInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        showHostsFragment()
    }

    private fun showHostsFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, HostsFragment.newInstance())
        }
    }

    override fun onHostInteraction(item: HostModel) {
        showToast("clicked: $item")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
