package com.example.tokukas

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val title: String,
    val publisher: String,
    val description: String,
    val year: Int,
    val price: Int,
    val stock: Int,
    val photo: String,
) : Parcelable
