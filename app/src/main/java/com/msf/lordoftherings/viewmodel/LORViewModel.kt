package com.msf.lordoftherings.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msf.lordoftherings.model.Movie
import com.msf.lordoftherings.model.MoviesResponse
import com.msf.lordoftherings.network.ResultWrapper
import com.msf.lordoftherings.repository.MoviesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LORViewModel(private val repository: MoviesRepository) : ViewModel() {

    private var searchJob: Job? = null

    private val _mutableLiveDataResults = MutableLiveData<List<Movie>>()
    val liveDataResults = _mutableLiveDataResults

    private val _mutableLoading = MutableLiveData<Boolean>()
    val liveDataLoading = _mutableLoading

    private val _mutableError = MutableLiveData<Error>()
    val liveDataError = _mutableError

    fun fetchMovies(){
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _mutableLoading.postValue(true)
            when (val result = repository.fetchAllMovies()) {
                is ResultWrapper.Success -> handleSuccess(result)
                is ResultWrapper.GenericError -> _mutableError.postValue(result.error!!)
                else -> _mutableError.postValue(Error("Something went wrong"))
            }
            _mutableLoading.postValue(false)
        }
    }

    private fun handleSuccess(result: ResultWrapper.Success<MoviesResponse>) {
        if (result.value.movies.isNotEmpty()) {
            _mutableLiveDataResults.postValue(result.value.movies)
        } else {
            _mutableError.postValue(Error("No movies found"))
        }
    }
}