package com.entreprisecorp.jammaster.models

import android.widget.TextView
import com.google.android.material.card.MaterialCardView

data class CardViewChord(
    val card: MaterialCardView,
    val chordName: TextView,
    val chordNumber: TextView
)