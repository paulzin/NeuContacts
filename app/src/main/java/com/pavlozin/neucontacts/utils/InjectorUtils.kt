package com.pavlozin.neucontacts.utils

import android.content.Context
import com.pavlozin.neucontacts.data.AppDatabase
import com.pavlozin.neucontacts.data.ContactsRepository
import com.pavlozin.neucontacts.viewmodels.ContactsViewModelFactory

object InjectorUtils {

    private fun getContactsRepository(context: Context) : ContactsRepository {
        return ContactsRepository.getInstance(AppDatabase.getInstance(context.applicationContext).getContactsDao())
    }

    fun provideContactsViewModelFactory(context: Context) : ContactsViewModelFactory {
        val repository = getContactsRepository(context)
        return ContactsViewModelFactory(repository)
    }
}