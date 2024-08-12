package com.kaankilic.movieapp.model

import com.google.gson.annotations.SerializedName

data class CastMember(
    @SerializedName("name")
    val name: String?,
    @SerializedName("character")
    val character: String?,
    @SerializedName("profile_path")
    val profile_path: String?
)
