package com.entreprisecorp.jammaster.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.FragmentRecyclerBinding
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter

abstract class RecyclerFragment : Fragment(R.layout.fragment_recycler) {

    lateinit var binding: FragmentRecyclerBinding
    val fastAdapter: GenericFastItemAdapter = GenericFastItemAdapter()

    private val recyclerViewManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(activity)

    abstract fun refreshScreen()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding = FragmentRecyclerBinding.bind(view)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.recyclerView.apply {
            layoutManager = recyclerViewManager
            this.adapter = fastAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        refreshScreen()
    }

    fun fillAdapter(items: List<GenericItem>) {
        fastAdapter.setNewList(items)
    }

}