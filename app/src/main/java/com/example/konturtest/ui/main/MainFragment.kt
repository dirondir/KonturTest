package com.example.konturtest.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.konturtest.R
import com.example.konturtest.databinding.MainFragmentBinding
import com.example.konturtest.ui.interfaces.SearchHolder
import com.example.konturtest.ui.main.recycler.ContactsListAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private lateinit var mBinding: MainFragmentBinding
    private val mViewModel: MainFragmentViewModel by viewModel()
    private lateinit var mContactsAdapter : ContactsListAdapter
    private lateinit var mSearchContactsAdapter : ContactsListAdapter

    private var mSearchHolder: SearchHolder? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.main_fragment, container, false
            )
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = viewLifecycleOwner

        mContactsAdapter = ContactsListAdapter {
            mViewModel.getContactInfo(it)
        }

        mSearchContactsAdapter = ContactsListAdapter {
            mViewModel.getContactInfo(it)
        }

        mViewModel.mSelectedItem.observe(viewLifecycleOwner){
            if (it != null)
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(it))
        }

        mBinding.rvContacts.apply {

            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = mContactsAdapter
        }

        mBinding.srlRefresh.setOnRefreshListener {
            mViewModel.loadContacts()
        }

        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel.mErrorText.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                Snackbar.make(requireContext(), mBinding.root, it, Snackbar.LENGTH_LONG).show()
                mViewModel.mErrorText.postValue("")
            }

        })

        mViewModel.mContacts.observe(viewLifecycleOwner, {
            mContactsAdapter.apply {
                clear()
                addData(it)
            }
            mBinding.srlRefresh.isRefreshing = false
        })

        mViewModel.mFoundContacts.observe(viewLifecycleOwner, {
            if (it.isEmpty())
                return@observe

            mSearchContactsAdapter.apply {
                clear()
                addData(it)
                if (it.isNotEmpty())
                    mBinding.rvContacts.adapter = this
            }
            mBinding.srlRefresh.isRefreshing = false
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SearchHolder) {
            mSearchHolder = context
            initSearch()
        }
        else {
            throw RuntimeException("$context must implement SearchHolder interface")
        }
    }

    private fun initSearch() {
        mSearchHolder?.setActions(
            {
                query : String? ->

                    mViewModel.search(query)
                    if (mBinding.rvContacts.adapter == mContactsAdapter)
                        mBinding.rvContacts.adapter = mSearchContactsAdapter

            },
            {
                mViewModel.finishSearch()
                mSearchContactsAdapter.clear()
                mBinding.rvContacts.adapter = mContactsAdapter
            })

    }

}