package com.akhutornoy.petclinic.ui.host

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhutornoy.petclinic.R
import com.akhutornoy.petclinic.di.ui.host.HostsUiInjection
import com.akhutornoy.petclinic.entity.ui.HostModel
import com.akhutornoy.petclinic.ui.base.BaseFragment
import com.akhutornoy.petclinic.ui.base.BaseViewModel
import com.akhutornoy.petclinic.ui.host.adapter.HostsRecyclerViewAdapter
import kotlinx.android.synthetic.main.add_host_dialog.*
import kotlinx.android.synthetic.main.fragment_hosts.*


class HostsFragment : BaseFragment() {

    private var listener: OnHostListInteractionListener? = null

    private val viewModel: HostsViewModel by lazy { HostsUiInjection.provideHostsViewModel(this) }
    private val listAdapter by lazy { HostsRecyclerViewAdapter(listener) }

    override fun fragmentLayoutId() = R.layout.fragment_hosts

    override fun initViewModelObservers() {
        viewModel.hosts.observe(this, Observer {
            listAdapter.items = it
        })
    }

    override fun initView() {
        // Set the adapter
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        fab.setOnClickListener {
            showAddHostDialog()
        }
    }

    override fun getBaseViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun getProgressBar(): View = progress_bar

    override fun loadData() {
        viewModel.getHosts()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnHostListInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnHostListInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun showAddHostDialog() {
        AddHostDialog().show(childFragmentManager, AddHostDialog::class.java.simpleName)
    }

    class AddHostDialog() : DialogFragment() {
        private val viewModel: HostsViewModel by lazy { HostsUiInjection.provideHostsViewModel(this) }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val title = getString(R.string.add_host)

            return AlertDialog.Builder(context!!, R.style.AppTheme_Dialog)
                .setTitle(title)
                .setView(R.layout.add_host_dialog)
                .setPositiveButton(R.string.all_ok) { _, _ ->  addHost() }
                .setNegativeButton(R.string.all_cancel) { _, _ ->  }
                .create()
        }

        private fun addHost() {

            viewModel.addHost(

                requireDialog().et_first_name.text.toString(),
                requireDialog().et_last_name.text.toString())
        }
    }

    interface OnHostListInteractionListener {
        fun onHostInteraction(item: HostModel)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HostsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}
