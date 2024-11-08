package com.example.musicplayer
//
//data class Data(
//    val album: Album,
//    val artist: Artist,
//    val duration: Int,
//    val explicit_content_cover: Int,
//    val explicit_content_lyrics: Int,
//    val explicit_lyrics: Boolean,
//    val id: Long,
//    val link: String,
//    val md5_image: String,
//    val preview: String,
//    val rank: Int,
//    val readable: Boolean,
//    val title: String,
//    val title_short: String,
//    val title_version: String,
//    val type: String
//)

import android.os.Parcel
import android.os.Parcelable

data class Data(
    val title: String,
    val album: Album,
    val preview: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readParcelable(Album::class.java.classLoader) ?: Album("", ""),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeParcelable(album, flags)
        parcel.writeString(preview)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data = Data(parcel)
        override fun newArray(size: Int): Array<Data?> = arrayOfNulls(size)
    }
}
