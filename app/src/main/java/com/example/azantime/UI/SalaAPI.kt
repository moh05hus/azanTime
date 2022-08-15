package com.example.azantime.UI

import com.example.azantime.model.State
import com.example.azantime.model.solat
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class salaAPI {
    var client = OkHttpClient()
    //get data from API
      fun getslalt(city : String):State<solat>{

           val request = Request.Builder()
               .url("https://muslimsalat.com/$city.json?key=2fa856a6a05f83c1c66388492572bfa1")
               .build()
          //make response fom client
          val response = client.newCall(request).execute()
         return if (response.isSuccessful){
            response.body?.string().run {
           val salat =  Gson().fromJson(this,solat::class.java)
                State.Success(salat)
            }
          }

          else{
              State.Fail(response.message)
          }

      }
}