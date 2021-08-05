package com.entreprisecorp.jammaster.models

data class Chord (
    val note: Note,
    val type: Type
) {
    fun toDisplay() : String {
        return "${note.toDisplay()}${type.toDisplay()}"
    }


}

enum class Type {
    MAJOR, MINOR, DIMINISHED
}

enum class Note {
    A, ASHARP, B, C, CSHARP, D, DSHARP, E, F, FSHARP, G, GSHARP
}


fun Note.toDisplay() : String {
    return when(this) {
        Note.A -> "A"
        Note.ASHARP -> "A#"
        Note.B -> "B"
        Note.C -> "C"
        Note.CSHARP -> "C#"
        Note.D -> "D"
        Note.DSHARP -> "D#"
        Note.E -> "E"
        Note.F -> "F"
        Note.FSHARP -> "F#"
        Note.G -> "G"
        Note.GSHARP -> "G#"
    }
}

fun Type.toDisplay(): String {
    return when(this) {
        Type.MAJOR -> ""
        Type.MINOR -> "m"
        Type.DIMINISHED -> "dim"
    }
}