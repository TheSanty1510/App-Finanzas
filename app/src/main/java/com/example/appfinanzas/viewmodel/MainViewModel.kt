package com.example.appfinanzas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.repository.FakeTransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainUiState(
    val income: Double = 0.0,
    val expenses: Double = 0.0,
    val balance: Double = 0.0,
    val recentTransactions: List<Transaction> = emptyList()
)

class MainViewModel : ViewModel() {
    // Inyectaríamos el repo, pero por ahora lo instanciamos
    private val repository = FakeTransactionRepository

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val summary = repository.getSummary()
            val transactions = repository.getRecentTransactions()
            _uiState.update {
                it.copy(
                    income = summary["income"] ?: 0.0,
                    expenses = summary["expenses"] ?: 0.0,
                    balance = summary["balance"] ?: 0.0,
                    recentTransactions = transactions
                )
            }
        }
    }
}