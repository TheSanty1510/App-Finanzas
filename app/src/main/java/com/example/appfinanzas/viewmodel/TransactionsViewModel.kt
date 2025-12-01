package com.example.appfinanzas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.network.RetrofitClient
import com.example.appfinanzas.repository.NetworkTransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TransactionsUiState(
    val selectedMonth: String = "Agosto",
    val transactions: List<Transaction> = emptyList()
)

class TransactionsViewModel : ViewModel() {
    private val repository = NetworkTransactionRepository(RetrofitClient.api)

    private val _uiState = MutableStateFlow(TransactionsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            val allTransactions = repository.getAllTransactions(_uiState.value.selectedMonth)
            _uiState.update { it.copy(transactions = allTransactions) }
        }
    }

    fun onMonthChanged(month: String) {
        _uiState.update { it.copy(selectedMonth = month) }
        loadTransactions() // Recargar transacciones para el nuevo mes
    }
}