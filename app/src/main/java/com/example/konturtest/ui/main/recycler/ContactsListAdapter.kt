package com.example.konturtest.ui.main.recycler

import com.example.konturtest.R
import com.example.konturtest.model.local.ContactMinimal


class ContactsListAdapter(callBack: (selectedContact: ContactMinimal) -> Unit) :
        BaseRecyclerViewAdapter<ContactMinimal>(callBack) {
    override fun getLayoutRes(viewType: Int) = R.layout.contact_item
}
