package com.akhutornoy.petclinic.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.commit
import com.akhutornoy.petclinic.R
import com.akhutornoy.petclinic.entity.ui.HostModel
import com.akhutornoy.petclinic.ui.base.BaseActivity
import com.akhutornoy.petclinic.ui.host.HostsFragment
import com.akhutornoy.petclinic.ui.host.OnHostClickedListener
import com.akhutornoy.petclinic.ui.pet.PetsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), OnHostClickedListener {

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

    override fun onHostClicked(hostModel: HostModel) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, PetsFragment.newInstance(hostModel.id))
            addToBackStack(PetsFragment.TAG)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}
