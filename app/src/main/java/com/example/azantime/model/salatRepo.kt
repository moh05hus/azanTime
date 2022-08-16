package com.example.azantime.model

import com.example.azantime.API.salaAPI
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