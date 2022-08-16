package com.example.azantime.API

import com.example.azantime.model.State
import com.example.azantime.model.solat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


class salatRepo {
    fun getSalat(city : String):Flow<State<solat>>{
        return flow {
            emit(State.Loading)
            try {
                emit(salaAPI().getslalt(city))
            }catch (e:Exception){
                emit(State.Fail(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}