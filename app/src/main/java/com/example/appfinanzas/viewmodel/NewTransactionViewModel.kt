package com.example.appfinanzas.viewmodel

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

    private val _uiState = MutableStateFlow(NewTransactionUiState())
    val uiState = _uiState.asStateFlow()

    fun onMontoChanged(monto: String) {
        _uiState.update { it.copy(monto = monto) }
    }

    fun onDescripcionChanged(descripcion: String) {
        _uiState.update { it.copy(descripcion = descripcion) }
    }

    fun onCategoriaChanged(categoria: String) {
        _uiState.update { it.copy(categoria = categoria) }
    }

    fun saveTransaction() {
        // Aquí iría la lógica para guardar en el repositorio
        // Por ahora, solo limpiamos el estado
        println("Guardando: ${uiState.value}")
        _uiState.value = NewTransactionUiState()
    }
}