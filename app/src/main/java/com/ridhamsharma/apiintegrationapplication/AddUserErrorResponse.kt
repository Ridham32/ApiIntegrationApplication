package com.ridhamsharma.apiintegrationapplication


import com.google.gson.annotations.SerializedName

class AddUserErrorResponse : ArrayList<AddUserErrorResponseItem>()
data class AddUserErrorResponseItem(
    @SerializedName("field")
    val field: String? = "", // email
    @SerializedName("message")
    val message: String? = "" // can't be blank
)