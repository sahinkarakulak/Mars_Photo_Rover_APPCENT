package com.mrcaracal.marsphoto_rover.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mrcaracal.marsphoto_rover.Utils.RetrofitRepository
import kotlinx.coroutines.Dispatchers

class MarsViewModel : ViewModel() {

    private val retrofitRepository: RetrofitRepository = RetrofitRepository()

    val data = liveData(Dispatchers.IO) {
        val marsData = retrofitRepository.getData()
        emit(marsData)
    }

    val data2 = liveData(Dispatchers.IO) {
        val marsData = retrofitRepository.getDataOpportunity()
        emit(marsData)
    }

    val data3 = liveData(Dispatchers.IO) {
        val marsData = retrofitRepository.getDataSpirit()
        emit(marsData)
    }

}