package com.example.appfinanzas.repository

import com.example.appfinanzas.model.Transaction

interface TransactionRepository {
    suspend fun getRecentTransactions(): List<Transaction>
    suspend fun getAllTransactions(month: String): List<Transaction>
    suspend fun saveTransaction(t: Transaction)
    suspend fun getSummary(): Map<String, Double>
}