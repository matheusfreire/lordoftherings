package com.msf.lordoftherings.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("_id")
    val id: String,
    val academyAwardNominations: Int,
    val academyAwardWins: Int,
    val boxOfficeRevenueInMillions: Double,
    val budgetInMillions: Int,
    val name: String,
    val rottenTomatoesScore: Double,
    val runtimeInMinutes: Int
)