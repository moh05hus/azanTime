package com.example.azantime

import android.content.Context
import com.example.azantime.model.Item
import com.example.azantime.model.solat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SalatPresenter(context: Context?){
    val salatView = context as ISalatView

    fun getDataFromAPI(city : String) {
        RetrofitService.create()
            .getAPI(city)
            .enqueue(object : Callback<solat> {


                override fun onFailure(call: Call<solat>, t: Throwable) {
                    salatView.onDataErrorFromApi(t)

                }
                override fun onResponse(call: Call<solat>, response: Response<solat>) {
                    salatView.onDataCompleteFromAPI(response.body()?.items?.get(0) as Item)
                }

            })
    }
}