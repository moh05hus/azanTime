package com.example.azantime.model

import com.example.azantime.UI.salaAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class salatRepo {
    fun getSalat(city : String):Flow<State<solat>>{
        return flow {
            emit(State.Loading)
            emit(salaAPI().getslalt(city))
        }.flowOn(Dispatchers.IO)
    }
}