package com.example.appfinanzas.repository

import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.network.ApiService

class NetworkTransactionRepository(private val api: ApiService) : TransactionRepository {

    override suspend fun getRecentTransactions(): List<Transaction> {
        return try {
            // Pedimos todas y tomamos las últimas 5 (simple por ahora)
            val all = api.getTransactions(null)
            all.takeLast(5).reversed()
        } catch (e: Exception) {
            emptyList() // O manejar el error
        }
    }

    override suspend fun getAllTransactions(month: String): List<Transaction> {
        return try {
            api.getTransactions(month)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getSummary(): Map<String, Double> {
        return try {
            api.getSummary()
        } catch (e: Exception) {
            mapOf("income" to 0.0, "expenses" to 0.0, "balance" to 0.0)
        }
    }

    // Función extra para guardar (que faltaba en tu interfaz original)
    override suspend fun saveTransaction(transaction: Transaction) {
        try {
            api.createTransaction(transaction)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}