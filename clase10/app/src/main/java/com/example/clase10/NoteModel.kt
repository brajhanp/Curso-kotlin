package com.example.clase10

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    var id: Int? = null,
    val title: String,
    val content: String
) : Parcelable
