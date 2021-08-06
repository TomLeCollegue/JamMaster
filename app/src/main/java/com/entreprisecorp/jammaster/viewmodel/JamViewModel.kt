package com.entreprisecorp.jammaster.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.entreprisecorp.jammaster.models.Chord
import com.entreprisecorp.jammaster.models.Note
import com.entreprisecorp.jammaster.models.Type

class JamViewModel : ViewModel() {

    val jamChords: MutableLiveData<ArrayList<Chord>> = MutableLiveData<ArrayList<Chord>>()
    val keyChords: MutableLiveData<List<Chord>> = MutableLiveData<List<Chord>>()

    init {
        val chords = listOf(
            Chord(Note.A, Type.MINOR),
            Chord(Note.B, Type.DIMINISHED),
            Chord(Note.C, Type.MAJOR),
            Chord(Note.D, Type.MINOR),
            Chord(Note.E, Type.MINOR),
            Chord(Note.F, Type.MAJOR),
            Chord(Note.G, Type.MAJOR),
        )
        keyChords.postValue(chords)
        jamChords.postValue(arrayListOf())
    }

    fun addJamChord(degrees: Int?) {
        val list = jamChords.value
        if (degrees != null) {
            keyChords.value?.get(degrees)?.let { list?.add(it) }
        }
        jamChords.postValue(list)
    }

    fun removeJamChord(degrees: Int?) {
        val list = jamChords.value
        list?.remove(jamChords.value?.first { it.degrees == degrees })
        jamChords.postValue(list)
    }

}