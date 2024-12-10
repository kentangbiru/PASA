package com.example.tugascapstonepasa.data.retrofit

import com.example.tugascapstonepasa.data.respone.PasswordResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST



interface ApiService {
    @POST("password/check")
    fun analyzePassword(@Body request: PasswordRequest): Call<PasswordResponse>

    data class PasswordRequest(
        val password: String // Password yang ingin dianalisis
    )
}