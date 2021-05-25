package com.example.konturtest.ui.details

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.konturtest.R
import com.example.konturtest.ui.interfaces.SearchHolder
import com.example.konturtest.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentDetailsBinding
    private var mSearchHolder: SearchHolder? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_details, container, false
            )
        mBinding.lifecycleOwner = viewLifecycleOwner

        mBinding.item = DetailsFragmentArgs.fromBundle(requireArguments()).Contact

        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is SearchHolder) {
            mSearchHolder = context
        }
         else {
            throw RuntimeException("$context must implement SearchHolder interface")
        }
    }

    override fun onResume() {
        super.onResume()
        mSearchHolder?.hideSearch()
    }

    override fun onDestroy() {
        super.onDestroy()
        mSearchHolder?.showSearch()
    }
}