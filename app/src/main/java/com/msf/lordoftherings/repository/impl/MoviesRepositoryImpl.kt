package com.msf.lordoftherings.repository.impl

import com.google.gson.Gson
import com.msf.lordoftherings.model.MoviesResponse
import com.msf.lordoftherings.network.ResultWrapper
import com.msf.lordoftherings.network.ResultWrapper.Success
import com.msf.lordoftherings.network.ResultWrapper.GenericError
import com.msf.lordoftherings.network.Service
import com.msf.lordoftherings.repository.MoviesRepository
import retrofit2.HttpException

class MoviesRepositoryImpl(private val service: Service) : MoviesRepository{
    override suspend fun fetchAllMovies(): ResultWrapper<MoviesResponse> {
        return try {
            val moviesFetched = service.fetchMovies()
            Success(moviesFetched)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val error = Gson().fromJson(errorBody, Error::class.java)
            GenericError(400, error)
        }
    }


}