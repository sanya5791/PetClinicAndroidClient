package com.akhutornoy.petclinic.ui.pet

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.akhutornoy.petclinic.R
import com.akhutornoy.petclinic.di.ui.host.PetsUiInjection
import com.akhutornoy.petclinic.entity.ui.PetModel
import com.akhutornoy.petclinic.ui.base.BaseFragment
import com.akhutornoy.petclinic.ui.base.BaseViewModel
import com.akhutornoy.petclinic.ui.pet.adapter.OnPetInteractionListener
import com.akhutornoy.petclinic.ui.pet.adapter.PetsRecyclerViewAdapter
import kotlinx.android.synthetic.main.add_pet_dialog.*
import kotlinx.android.synthetic.main.fragment_pets.*

class PetsFragment : BaseFragment() {

    private val viewModel: PetsViewModel by lazy { PetsUiInjection.providePetsViewModel(this) }
    private val listAdapter by lazy { PetsRecyclerViewAdapter(getOnPetInteractionListener()) }

    private fun getOnPetInteractionListener() = object : OnPetInteractionListener {
        override fun onPetDeleteClicked(item: PetModel) {
            viewModel.deletePet(item.id)
        }
    }

    override fun fragmentLayoutId() = R.layout.fragment_pets

    override fun initViewModelObservers() {
        viewModel.pets.observe(this, Observer {
            listAdapter.items = it
        })
    }

    override fun initView() {
        // Set the adapter
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        swipe_refresh.setOnRefreshListener {
            viewModel.getPets()
            swipe_refresh.isRefreshing = false
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
        viewModel.getPets()
    }

    private fun showAddHostDialog() {
        AddPetDialog().show(childFragmentManager, AddPetDialog::class.java.simpleName)
    }

    class AddPetDialog() : DialogFragment() {
        private val viewModel: PetsViewModel by lazy { PetsUiInjection.providePetsViewModel(this) }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val title = getString(R.string.add_host)

            return AlertDialog.Builder(context!!, R.style.AppTheme_Dialog)
                .setTitle(title)
                .setView(R.layout.add_pet_dialog)
                .setPositiveButton(R.string.all_ok) { _, _ -> addHost() }
                .setNegativeButton(R.string.all_cancel) { _, _ -> }
                .create()
        }

        private fun addHost() {

            viewModel.addPet(
                requireDialog().et_name.text.toString(),
                requireDialog().et_breed.text.toString()
            )
        }
    }

    companion object {
        val TAG = PetsFragment::class.java.name
        const val ARG_HOST_ID = "ARG_HOST_ID"
        @JvmStatic
        fun newInstance(hostId: Long) =
            PetsFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_HOST_ID, hostId)
                }
            }
    }

}
