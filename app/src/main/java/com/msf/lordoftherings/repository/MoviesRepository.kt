package com.msf.lordoftherings.repository

import com.msf.lordoftherings.model.MoviesResponse
import com.msf.lordoftherings.network.ResultWrapper

interface MoviesRepository {
    suspend fun fetchAllMovies(): ResultWrapper<MoviesResponse>
}