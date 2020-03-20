package com.pavlozin.neucontacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pavlozin.neucontacts.data.Contact
import com.pavlozin.neucontacts.utils.InjectorUtils
import com.pavlozin.neucontacts.utils.KEY_CONTACT
import com.pavlozin.neucontacts.viewmodels.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_edit_contact.*

class EditContactFragment : Fragment() {

    private val viewModel: ContactsViewModel by viewModels {
        InjectorUtils.provideContactsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var shouldCreateNewUser = true

        val existingContact = arguments?.getParcelable<Contact>(KEY_CONTACT)

        existingContact?.let {
            first_name_edit_text.setText(it.firstName)
            last_name_edit_text.setText(it.lastName)
            phone_edit_text.setText(it.phone)
            email_edit_text.setText(it.email)
            address_edit_text.setText(it.address)
            city_edit_text.setText(it.city)
            zip_edit_text.setText(it.zipCode.toString())

            submit_contact_button.text = getString(R.string.save_contact_button_text)
            titleTextView.text = getString(R.string.edit_contact_title)
            delete_contact_button.visibility = View.VISIBLE

            delete_contact_button.setOnClickListener {
                viewModel.deleteContact(existingContact)
                findNavController().popBackStack()
            }

            shouldCreateNewUser = false
        }

        submit_contact_button.setOnClickListener {
            if (!areAllFieldsValid()) {
                Snackbar.make(view, "All fields are required", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val contact = Contact(
                id = existingContact?.id ?: 0,
                firstName = first_name_edit_text.text.toString(),
                lastName = last_name_edit_text.text.toString(),
                phone = phone_edit_text.text.toString(),
                email = email_edit_text.text.toString(),
                address = address_edit_text.text.toString(),
                city = city_edit_text.text.toString(),
                zipCode = zip_edit_text.text.toString().toInt()
            )

            if (shouldCreateNewUser) {
                viewModel.addContact(contact)
            } else {
                viewModel.updateContact(contact)
            }
            findNavController().popBackStack()
        }
    }

    private fun areAllFieldsValid(): Boolean {
        return first_name_edit_text.text.toString().isNotBlank() &&
                last_name_edit_text.text.toString().isNotBlank() &&
                phone_edit_text.text.toString().isNotBlank() &&
                email_edit_text.text.toString().isNotBlank() &&
                address_edit_text.text.toString().isNotBlank() &&
                city_edit_text.text.toString().isNotBlank() &&
                zip_edit_text.text.toString().isNotBlank()
    }
}
