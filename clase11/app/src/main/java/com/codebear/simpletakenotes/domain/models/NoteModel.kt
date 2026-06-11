package com.codebear.simpletakenotes.domain.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    val title: String,
    val content: String,
    val createdAt: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeLong(createdAt)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<NoteModel> {
        override fun createFromParcel(parcel: Parcel): NoteModel = NoteModel(parcel)
        override fun newArray(size: Int): Array<NoteModel?> = arrayOfNulls(size)
    }
}
