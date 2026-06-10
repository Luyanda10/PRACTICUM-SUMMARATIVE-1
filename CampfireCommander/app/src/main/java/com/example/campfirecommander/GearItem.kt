package com.example.campfirecommander

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GearItem(
    val item: String,
    val category: String,
    val quantity: String,
    val comment: String
) : Parcelable
