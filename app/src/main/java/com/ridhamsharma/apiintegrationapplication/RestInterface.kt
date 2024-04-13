package com.ridhamsharma.apiintegrationapplication

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RestInterface {

    @GET("users")
    fun getUsers() : Call<GetResponse>

    @POST("users")
    @FormUrlEncoded
    fun addUser(@Header("Authorization") header: String,
                @Field("name") name1: String,
                @Field("email") email: String,
                @Field("gender") gender: String,
                @Field("status") status: String,
                ): Call<GetResponseItem>
}