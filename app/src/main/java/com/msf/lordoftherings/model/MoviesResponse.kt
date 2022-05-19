package com.msf.lordoftherings.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("docs")
    val movies: List<Movie>,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)