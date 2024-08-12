package com.kaankilic.movieapp.model

import com.google.gson.annotations.SerializedName

data class CreditsResponse(
    @SerializedName("cast")
    val cast: List<CastMember>
)