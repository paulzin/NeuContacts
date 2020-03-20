package com.pavlozin.neucontacts.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pavlozin.neucontacts.data.ContactsRepository

class ContactsViewModelFactory(
    private val repository: ContactsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(repository) as T
    }
}