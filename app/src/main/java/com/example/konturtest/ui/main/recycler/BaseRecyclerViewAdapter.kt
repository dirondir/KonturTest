package com.example.konturtest.ui.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(private val callbacks: ((T)->Unit)? = null) :
    RecyclerView.Adapter<ContactItemViewHolder<T>>() {

    private var _items: MutableList<T> = mutableListOf()

    private val items: List<T>?
        get() = this._items

    override fun getItemCount() = _items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil
            .inflate<ViewDataBinding>(layoutInflater, getLayoutRes(viewType), parent, false)

        binding.lifecycleOwner = getLifecycleOwner()

        return ContactItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactItemViewHolder<T>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            callbacks?.invoke(item)
        }
    }

    fun getItem(position: Int) = _items[position]

    /**
     * Adds data to the actual Dataset
     *
     * @param items to be merged
     */
    fun addData(items: List<T>) {
        _items.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * Clears the _items data
     */
    fun clear() {
        _items.clear()
        notifyDataSetChanged()
    }

    @LayoutRes
    abstract fun getLayoutRes(viewType: Int): Int

    open fun getLifecycleOwner(): LifecycleOwner? {
        return null
    }
}