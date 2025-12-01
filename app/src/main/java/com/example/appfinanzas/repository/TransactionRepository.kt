package com.example.appfinanzas.repository

import com.example.appfinanzas.model.Transaction

interface TransactionRepository {
    fun getRecentTransactions(): List<Transaction>
    fun getAllTransactions(month: String): List<Transaction>
    fun getSummary(): Map<String, Double>
}