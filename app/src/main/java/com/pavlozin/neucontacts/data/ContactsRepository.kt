package com.pavlozin.neucontacts.data

class ContactsRepository private constructor(private val contactsDao: ContactsDao) {
    fun getContacts() = contactsDao.getAllContacts()

    suspend fun addContact(contact: Contact) {
        contactsDao.insertContact(contact)
    }
    suspend fun updateContact(contact: Contact) {
        contactsDao.updateContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        contactsDao.deleteContact(contact)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: ContactsRepository? = null

        fun getInstance(contactsDao: ContactsDao) =
            instance ?: synchronized(this) {
                instance ?: ContactsRepository(contactsDao).also { instance = it }
            }
    }
}