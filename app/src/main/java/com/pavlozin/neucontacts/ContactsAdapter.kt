package com.pavlozin.neucontacts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pavlozin.neucontacts.data.Contact
import com.pavlozin.neucontacts.utils.KEY_CONTACT
import kotlinx.android.synthetic.main.contact_list_item.view.*

class ContactsAdapter : ListAdapter<Contact, ContactsAdapter.ViewHolder>(ContactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(contact: Contact) = with(itemView) {
            this.nameTextView.text = "${contact.firstName} ${contact.lastName}"
            this.phoneTextView.text = contact.phone
            this.setOnClickListener {
                itemView.findNavController().navigate(
                    R.id.action_mainFragment_to_editContactFragment, bundleOf(
                        KEY_CONTACT to contact
                    )
                )
            }
        }
    }

    private class ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {

        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }
}