package com.entreprisecorp.jammaster.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.FragmentRecyclerBinding
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter

abstract class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    private lateinit var binding: FragmentRecyclerBinding
    val fastAdapter = GenericFastItemAdapter()

    val recyclerViewManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(activity)

    val supportActionBar : ActionBar? by lazy {
        (activity as AppCompatActivity).supportActionBar
    }


    abstract fun refreshScreen()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecyclerBinding.bind(view)
        binding.recyclerView.apply {
            layoutManager = recyclerViewManager
            this.adapter = fastAdapter
        }
        refreshScreen()
    }

    fun fillAdapter(items: List<GenericItem>) {
        fastAdapter.setNewList(items)
    }

}