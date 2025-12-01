package com.example.appfinanzas.network

import com.example.appfinanzas.model.Transaction
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // 1. Obtener transacciones (opcionalmente por mes)
    @GET("transactions")
    suspend fun getTransactions(@Query("month") month: String?): List<Transaction>

    // 2. Guardar nueva transacción
    @POST("transactions")
    suspend fun createTransaction(@Body transaction: Transaction): Response<Unit>

    // 3. Obtener resumen
    @GET("transactions/summary")
    suspend fun getSummary(): Map<String, Double>
}