package com.ctt.heroismarvel.service

import com.ctt.heroismarvel.model.Bonecos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BonecosService {
    //hash=a3ac66922202f6f9d788f787cb46df01&ts=2000&name={nomeBoneco}

    @GET("v1/public/characters")
    fun buscarBonecos(
        @Query("apikey") key: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("name") nomeBoneco: String

    ): Call<Bonecos>

}