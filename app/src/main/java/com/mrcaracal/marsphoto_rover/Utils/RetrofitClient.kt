package com.mrcaracal.marsphoto_rover.Utils

import com.mrcaracal.marsphoto_rover.Models.Data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitClient {

    val BASE_URL = "https://api.nasa.gov/mars-photos/"
    val retrofitService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }

}

interface RetrofitService {

    @GET("api/v1/rovers/curiosity/photos?sol=900&api_key=cJoyDKbVhUx8CLDZy0tiaE24FZX0rOdFyAdkdabD")
    suspend fun getMarsData(): Data

    @GET("api/v1/rovers/opportunity/photos?sol=900&api_key=cJoyDKbVhUx8CLDZy0tiaE24FZX0rOdFyAdkdabD")
    suspend fun getMarsDataOpportunity(): Data

    @GET("api/v1/rovers/spirit/photos?sol=900&api_key=cJoyDKbVhUx8CLDZy0tiaE24FZX0rOdFyAdkdabD")
    suspend fun getMarsDataSpirit(): Data
}