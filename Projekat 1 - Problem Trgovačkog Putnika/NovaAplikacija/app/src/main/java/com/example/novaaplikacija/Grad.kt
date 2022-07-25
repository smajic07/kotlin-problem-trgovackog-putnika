package com.example.novaaplikacija
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
class Grad (var naziv: String, var longituda: String, var latituda: String, var drzava: String, var opis: String) : Parcelable

