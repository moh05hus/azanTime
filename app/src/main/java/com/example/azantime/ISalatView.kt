package com.example.azantime

import com.example.azantime.model.Item

interface ISalatView{
    fun onDataCompleteFromAPI(salat:Item)
    fun onDataErrorFromApi(throwabal: Throwable)

}