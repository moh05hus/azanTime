package com.example.azantime.API

import com.example.azantime.model.State
import com.example.azantime.model.salat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception


class SalatRepo {
    fun getSalat(city : String):Flow<State<salat>>{
        return flow {
            emit(State.Loading)
            try {
                emit(salaAPI().getSlalt(city))
            }catch (e:Exception){
                emit(State.Fail(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}