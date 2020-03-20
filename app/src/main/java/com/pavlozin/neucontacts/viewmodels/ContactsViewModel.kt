package com.pavlozin.neucontacts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pavlozin.neucontacts.data.Contact
import com.pavlozin.neucontacts.data.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel internal constructor(private val repository: ContactsRepository) : ViewModel() {
    val contacts: LiveData<List<Contact>> = repository.getContacts()

    fun addContact(contact: Contact) {
        viewModelScope.launch {
            repository.addContact(contact)
        }
    }

    fun updateContact(contact: Contact) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
}
