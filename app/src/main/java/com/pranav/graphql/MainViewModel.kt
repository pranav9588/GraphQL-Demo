package com.pranav.graphql

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pranav.GetCountriesQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {
    var graphQLResponseModel: MutableLiveData<List<GetCountriesQuery.Country>?> = MutableLiveData()

    init {
        getContinents()
    }

    private fun getContinents() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val apiResponse = mainRepository.getCountries()
            println("response -> $apiResponse")
            graphQLResponseModel.postValue(apiResponse)
        } catch (exception: Exception) {
            Log.e("APPLICATION ERROR", exception.message.toString())
        }
    }

}