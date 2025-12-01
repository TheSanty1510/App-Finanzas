package com.example.appfinanzas.viewmodel

import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.model.TransactionType
import java.time.LocalDate
import com.example.appfinanzas.network.RetrofitClient
import com.example.appfinanzas.repository.NetworkTransactionRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class NewTransactionUiState(
    val monto: String = "",
    val descripcion: String = "",
    val categoria: String = ""
)

class NewTransactionViewModel : ViewModel() {
    private val repository = NetworkTransactionRepository(RetrofitClient.api)
    private val _uiState = MutableStateFlow(NewTransactionUiState())
    val uiState = _uiState.asStateFlow()

    fun saveTransaction() {
        val currentState = _uiState.value
    val newTransaction = Transaction(
        id = "", // La API/DB generará el ID, puedes mandar vacío o null
        detail = currentState.categoria,
        description = currentState.descripcion,
        amount = currentState.monto.toDoubleOrNull() ?: 0.0,
        type = TransactionType.EXPENSE // OJO: Debes pasar el tipo correcto desde la UI
    )

    viewModelScope.launch {
        repository.saveTransaction(newTransaction)
        // Limpiar estado UI
        _uiState.value = NewTransactionUiState()
        }
    }


    fun onMontoChanged(monto: String) {
        _uiState.update { it.copy(monto = monto) }
    }

    fun onDescripcionChanged(descripcion: String) {
        _uiState.update { it.copy(descripcion = descripcion) }
    }

    fun onCategoriaChanged(categoria: String) {
        _uiState.update { it.copy(categoria = categoria) }
    }

}