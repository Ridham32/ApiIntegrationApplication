package com.ridhamsharma.apiintegrationapplication

import com.google.gson.annotations.SerializedName

class GetResponse : ArrayList<GetResponseItem>()

data class GetResponseItem(
    @SerializedName("email")
    val email: String? = "", // himadri_miss_kaur@schaefer.test
    @SerializedName("gender")
    val gender: String? = "", // female
    @SerializedName("id")
    val id: Int? = 0, // 6850172
    @SerializedName("name")
    val name: String? = "", // Miss Himadri Kaur
    @SerializedName("status")
    val status: String? = "" // active
)
