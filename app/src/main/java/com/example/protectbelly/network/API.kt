package com.example.protectbelly.network

import com.example.protectbelly.models.ExerciseResponseObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    public val BASE_URL: String
        get() = "https://wger.de/api/v2/"

    @GET("exercise?limit=386&language=2")
    public fun getAllExercises(): Call<ExerciseResponseObject>;

    @GET("exercise")
    public fun getExerciseByName(@Query("name") name:String, @Query("language") language:Int = 2): Call<ExerciseResponseObject>;
}