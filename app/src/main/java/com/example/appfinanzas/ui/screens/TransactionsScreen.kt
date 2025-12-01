package com.example.appfinanzas.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appfinanzas.ui.components.TransactionItemFullRow
import com.example.appfinanzas.viewmodel.TransactionsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    viewModel: TransactionsViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Selector de Mes (Simulado)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Mes:", modifier = Modifier.padding(end = 8.dp))
            OutlinedTextField(
                value = uiState.selectedMonth,
                onValueChange = { /* No funcional */ },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                modifier = Modifier.width(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Encabezado de la lista
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(text = "Detalle", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text(text = "Descripción", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold)
            Text(
                text = "Monto",
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.End
            )
        }

        // Lista de transacciones
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(uiState.transactions) { transaction ->
                TransactionItemFullRow(transaction = transaction)
            }
        }

        // Paginación (Simulada)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { /* No funcional */ }) {
                Text(text = "Pag. Anterior")
            }
            Text(text = "1 de 1") // Simulado
            TextButton(onClick = { /* No funcional */ }) {
                Text(text = "Pag. Siguiente")
            }
        }
    }
}