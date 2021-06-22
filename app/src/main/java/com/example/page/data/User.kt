package com.example.page.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

    @SerializedName("login")
    var id: Login,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("name")
    var name: UserName,
    @SerializedName("dob")
    var dob: UserDob,
    @SerializedName("picture")
    var photo: UserPhoto,
    @SerializedName("cell")
    var cell: String,
    @SerializedName("phone")
    var phone: String
) : Parcelable {

    @Parcelize
    data class Login(@SerializedName("uuid") val uuid: String) : Parcelable

    @Parcelize
    data class UserName(
        @SerializedName("first")
        val firstName: String,
        @SerializedName("last")
        val lastName: String
    ) : Parcelable

    @Parcelize
    data class UserDob(@SerializedName("date") val date: String) : Parcelable


    @Parcelize
    data class UserPhoto(
        @SerializedName("large")
        val large: String,
        @SerializedName("medium")
        val medium: String,
        @SerializedName("thumbnail")
        val thumbnail: String
    ) : Parcelable

    @Parcelize
    data class Info(
        @SerializedName("seed")
        val seed: String,
        @SerializedName("results")
        val results: Long,
        @SerializedName("page")
        val page: Long,
        @SerializedName("version")
        val version: String
    ) : Parcelable
}