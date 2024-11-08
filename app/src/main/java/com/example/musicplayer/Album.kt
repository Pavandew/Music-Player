package com.example.musicplayer

//data class Album(
//    val cover: String,
//    val cover_big: String,
//    val cover_medium: String,
//    val cover_small: String,
//    val cover_xl: String,
//    val id: Int,
//    val md5_image: String,
//    val title: String,
//    val tracklist: String,
//    val type: String
//)

import android.os.Parcel
import android.os.Parcelable

// Album class definition
data class Album(
    val cover: String,
    val artist: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cover)
        parcel.writeString(artist)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album = Album(parcel)
        override fun newArray(size: Int): Array<Album?> = arrayOfNulls(size)
    }
}
