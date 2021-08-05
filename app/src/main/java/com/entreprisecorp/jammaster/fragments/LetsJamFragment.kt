package com.entreprisecorp.jammaster.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.res.use
import androidx.fragment.app.Fragment
import com.entreprisecorp.jammaster.R
import com.entreprisecorp.jammaster.databinding.FragmentJamBinding
import com.entreprisecorp.jammaster.fastitems.KeyItem
import com.entreprisecorp.jammaster.models.CardViewChord
import com.entreprisecorp.jammaster.models.Chord
import com.entreprisecorp.jammaster.models.Note
import com.entreprisecorp.jammaster.models.Type
import com.google.android.material.card.MaterialCardView
import com.google.android.material.transition.MaterialContainerTransform
import com.mikepenz.fastadapter.GenericItem

class LetsJamFragment : Fragment(R.layout.fragment_jam) {

    private lateinit var binding: FragmentJamBinding
    private var cardViews = listOf<CardViewChord>()
    private val chords = listOf(
        Chord(Note.A, Type.MINOR),
        Chord(Note.B, Type.DIMINISHED),
        Chord(Note.C, Type.MAJOR),
        Chord(Note.D, Type.MINOR),
        Chord(Note.E, Type.MINOR),
        Chord(Note.F, Type.MAJOR),
        Chord(Note.G, Type.MAJOR),
    )

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

        chords.forEachIndexed { index, chord ->
            cardViews[index].apply {
                chordName.text = chord.toDisplay()
                chordNumber.text = index.toDegrees()
                card.setOnClickListener {
                    card.isChecked = !card.isChecked
                }
            }

        }
        refreshScreen()
    }


    private fun refreshScreen() {

    }
}

private fun Int.toDegrees(): String {
    return when(this){
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