package com.example.azantime

import com.example.azantime.model.solat
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService{
    @GET("{city}.json?key=2fa856a6a05f83c1c66388492572bfa1")
    fun getAPI(@Path("city") city:String):Call<solat>
    companion object{
        fun create() : RetrofitService{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://muslimsalat.com")
                .build()
            return retrofit.create(RetrofitService :: class.java)

        }
    }
}

