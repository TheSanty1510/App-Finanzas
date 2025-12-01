package com.example.appfinanzas.repository

import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.model.TransactionType

// Usamos 'object' para un Singleton simple, ya que no necesitamos DI avanzada aún
object FakeTransactionRepository : TransactionRepository {

    private val sampleTransactions = listOf(
        Transaction("1", "Alimentos 20/10/2025", "McDonald's", -250.00, TransactionType.EXPENSE),
        Transaction("2", "Luz 19/10/2025", "Enel", -800.00, TransactionType.EXPENSE),
        Transaction("3", "Sueldo 15/10/2025", "Trabajo", 10000.00, TransactionType.INCOME),
        Transaction("4", "Transporte 18/10/2025", "Metro", -50.00, TransactionType.EXPENSE),
    )

    override fun getRecentTransactions(): List<Transaction> {
        // Devuelve solo las 3 más recientes
        return sampleTransactions.take(3)
    }

    override fun getAllTransactions(month: String): List<Transaction> {
        // En un futuro, filtrarías por 'month'. Ahora solo devolvemos todo.
        return sampleTransactions
    }

    override fun getSummary(): Map<String, Double> {
        val income = sampleTransactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expenses = sampleTransactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }
        return mapOf(
            "income" to income,
            "expenses" to expenses,
            "balance" to (income + expenses) // expenses ya es negativo
        )
    }
}