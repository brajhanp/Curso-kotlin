package com.example.clase7

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    val id: Int,
    val title: String,
    val content: String,
    val date: String
) : Parcelable
