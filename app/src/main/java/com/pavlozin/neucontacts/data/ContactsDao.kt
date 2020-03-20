package com.pavlozin.neucontacts.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contact>>

    @Insert
    suspend fun insertContact(vararg contact: Contact)

    @Update
    suspend fun updateContact(vararg contact: Contact)

    @Delete
    suspend fun deleteContact(vararg contact: Contact)
}
