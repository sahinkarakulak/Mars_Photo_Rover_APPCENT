package com.mrcaracal.marsphoto_rover.Utils

class RetrofitRepository {

    var retrofitClient: RetrofitService = RetrofitClient().retrofitService
    suspend fun getData() = retrofitClient.getMarsData()
    suspend fun getDataOpportunity() = retrofitClient.getMarsDataOpportunity()
    suspend fun getDataSpirit() = retrofitClient.getMarsDataSpirit()
}