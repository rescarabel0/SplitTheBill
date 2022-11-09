package br.edu.ifsp.ads.pdm.splitthebill.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payer(
    val id: Int,
    val name: String,
    val paid: Double,
    val bought: String
) : Parcelable