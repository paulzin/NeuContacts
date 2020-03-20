package com.pavlozin.neucontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pavlozin.neucontacts.utils.InjectorUtils
import com.pavlozin.neucontacts.viewmodels.ContactsViewModel
import kotlinx.android.synthetic.main.contacts_fragment.*

class ContactsFragment : Fragment() {

    private val viewModel: ContactsViewModel by viewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.contacts_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addContactButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_editContactFragment)
        }

        val contactsAdapter = ContactsAdapter()

        contactsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = contactsAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

        observeViewModel(contactsAdapter)
    }

    private fun observeViewModel(contactsAdapter: ContactsAdapter) {
        viewModel.contacts.observe(viewLifecycleOwner, Observer {
            contactsAdapter.submitList(it)
        })
    }
}
