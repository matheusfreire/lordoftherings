package com.msf.lordoftherings.network

import com.msf.lordoftherings.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("movie")
    suspend fun fetchMovies() : MoviesResponse

    @GET("character")
    suspend fun fetchCharacters(@Query("page") page: Int, @Query("limit") limit: Int = 10)

    @GET("character/{id}")
    suspend fun fetchCharacterDetails(@Path("id") id: String)
}