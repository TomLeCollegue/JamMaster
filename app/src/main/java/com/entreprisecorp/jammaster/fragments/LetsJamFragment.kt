package com.entreprisecorp.jammaster.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.FragmentJamBinding
import com.entreprisecorp.jammaster.fastitems.ChordJamItem
import com.entreprisecorp.jammaster.models.CardViewChord
import com.entreprisecorp.jammaster.viewmodel.JamViewModel
import com.google.android.material.transition.MaterialContainerTransform
import com.mikepenz.fastadapter.GenericItem
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.drag.ItemTouchCallback
import com.mikepenz.fastadapter.drag.SimpleDragCallback
import com.mikepenz.fastadapter.utils.DragDropUtil

class LetsJamFragment : Fragment(R.layout.fragment_jam), ItemTouchCallback {

    private lateinit var binding: FragmentJamBinding
    private var cardViews = listOf<CardViewChord>()

    private val viewModel: JamViewModel by viewModels()
    private val adapterJam: GenericFastItemAdapter = GenericFastItemAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentJamBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.transitionName = getString(R.string.email_card_detail_transition_name)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.navHostFragment
            duration = resources.getInteger(R.integer.duration_transition).toLong()
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
        binding.apply {
            cardViews = listOf(
                CardViewChord(ICardView, IChordTextView, INumberTextView),
                CardViewChord(IICardView, IIChordTextView, IINumberTextView),
                CardViewChord(IIICardView, IIIChordTextView, IIINumberTextView),
                CardViewChord(IVCardView, IVChordTextView, IVNumberTextView),
                CardViewChord(VCardView, VChordTextView, VNumberTextView),
                CardViewChord(VICardView, VIChordTextView, VINumberTextView),
                CardViewChord(VIICardView, VIIChordTextView, VIINumberTextView),
            )
        }

        viewModel.keyChords.observe(viewLifecycleOwner) {
            it.forEachIndexed { index, chord ->
                cardViews[index].apply {
                    chordName.text = chord.toDisplay()
                    chordNumber.text = index.toDegrees()
                    chord.degrees = index
                    card.setOnClickListener {
                        card.isChecked = !card.isChecked
                        if (card.isChecked) {
                            viewModel.addJamChord(chord.degrees)
                        } else {
                            viewModel.removeJamChord(chord.degrees)
                        }
                    }
                }
            }
        }

        binding.recyclerViewJam.apply {
            val dragCallback = SimpleDragCallback(SimpleDragCallback.LEFT_RIGHT, this@LetsJamFragment)
            val touchHelper = ItemTouchHelper(dragCallback)
            touchHelper.attachToRecyclerView(this)
            adapter = adapterJam
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        }


        viewModel.jamChords.observe(viewLifecycleOwner) {
            Log.d("observe", it.toString())
            refreshJamScreen()
        }

    }

    private fun refreshJamScreen() {
        val items = arrayListOf<GenericItem>()
        viewModel.jamChords.value?.forEach {
            items += ChordJamItem(it)
        }
        adapterJam.setNewList(items)
    }

    override fun itemTouchOnMove(oldPosition: Int, newPosition: Int): Boolean {
        DragDropUtil.onMove(adapterJam.itemAdapter, oldPosition, newPosition) // change position
        return true
    }

    override fun itemTouchDropped(oldPosition: Int, newPosition: Int) {
        val list = viewModel.jamChords.value
        list?.moveAt(oldPosition, newPosition)
        viewModel.jamChords.postValue(list)
    }
}

fun Int.toDegrees(): String {
    return when (this) {
        0 -> "I"
        1 -> "II"
        2 -> "III"
        3 -> "IV"
        4 -> "V"
        5 -> "VI"
        6 -> "VII"
        else -> "?"
    }
}

@ColorInt
@SuppressLint("Recycle")
fun Context.themeColor(
    @AttrRes themeAttrId: Int
): Int {
    return obtainStyledAttributes(
        intArrayOf(themeAttrId)
    ).use {
        it.getColor(0, Color.MAGENTA)
    }
}

/**
 * Moves the given item at the `oldIndex` to the `newIndex`
 */
fun <T> MutableList<T>.moveAt(oldIndex: Int, newIndex: Int) {
    val item = this[oldIndex]
    removeAt(oldIndex)
    if (oldIndex > newIndex)
        add(newIndex, item)
    else
        add(newIndex - 1, item)
}