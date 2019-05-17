package com.akhutornoy.petclinic.ui.host

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhutornoy.petclinic.R
import com.akhutornoy.petclinic.entity.ui.HostModel
import com.akhutornoy.petclinic.ui.base.BaseFragment
import com.akhutornoy.petclinic.ui.base.BaseViewModel
import com.akhutornoy.petclinic.ui.dummy.DummyContent
import com.akhutornoy.petclinic.ui.host.adapter.HostsRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_hosts.*

class HostsFragment : BaseFragment() {

    private var listener: OnHostListInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun fragmentLayoutId() = R.layout.fragment_hosts

    override fun initViewModelObservers() {
//        TODO:
    }

    override fun initView() {
        // Set the adapter
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = HostsRecyclerViewAdapter(DummyContent.ITEMS, listener)
        }
    }

    override fun getBaseViewModel(): BaseViewModel? {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return null
    }

    override fun getProgressBar(): View = progress_bar

    override fun loadData() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    interface OnHostListInteractionListener {
        // TODO: Update argument type and name
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
