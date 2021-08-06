package com.entreprisecorp.jammaster.fastitems

import android.view.LayoutInflater
import android.view.ViewGroup
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.ChordJamItemBinding
import com.entreprisecorp.jammaster.fragments.toDegrees
import com.entreprisecorp.jammaster.models.Chord
import com.entreprisecorp.jammaster.models.toDisplay
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.drag.IDraggable

class ChordJamItem(private val chord: Chord) : AbstractBindingItem<ChordJamItemBinding>(), IDraggable {

    override val type: Int = R.id.item_chord_jam
    override val isDraggable: Boolean = true

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ChordJamItemBinding {
        return ChordJamItemBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ChordJamItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.apply {
            numberTextView.text = chord.degrees?.toDegrees()
            chordTextView.text = chord.toDisplay()
        }
    }
}