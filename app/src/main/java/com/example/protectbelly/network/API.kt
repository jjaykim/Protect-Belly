package com.example.protectbelly.network

import com.example.protectbelly.models.ExerciseResponseObject
import retrofit2.Call
import retrofit2.http.GET

interface API {
    public val BASE_URL: String
        get() = "https://wger.de/api/v2/"

    @GET("exercise?limit=386")
    public fun getAllExercises(): Call<ExerciseResponseObject>;
}