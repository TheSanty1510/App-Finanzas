package com.example.appfinanzas.model

data class Transaction(
    val id: String,
    val detail: String,
    val description: String,
    val amount: Double,
    val type: TransactionType
)