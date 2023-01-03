package com.example.youtube

import retrofit2.http.POST
import com.example.youtube.LoginResult
import retrofit2.Call
import retrofit2.http.Body
import java.util.HashMap

interface RetrofitInterface {
    @POST("user/login")
    fun executeLogin(@Body map: HashMap<String?, String?>?): Call<LoginResult?>?

    @POST("user/signup")
    fun executeSignup(@Body map: HashMap<String?, String?>?): Call<Void?>?
}