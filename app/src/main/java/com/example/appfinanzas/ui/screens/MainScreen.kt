package com.example.appfinanzas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appfinanzas.ui.components.SummaryBox
import com.example.appfinanzas.ui.components.TransactionItem
import com.example.appfinanzas.ui.theme.AppFinanzasTheme
import com.example.appfinanzas.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onNavigateToNewIncome: () -> Unit,
    onNavigateToNewExpense: () -> Unit,
    onNavigateToTransactions: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Control de Gastos", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Resumen Financiero", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SummaryBox(title = "Ingresos", amount = uiState.income, Modifier.weight(1f))
            SummaryBox(title = "Gastos", amount = uiState.expenses, Modifier.weight(1f))
            SummaryBox(title = "Balance", amount = uiState.balance, Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onNavigateToNewIncome,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC8E6C9))
            ) {
                Text(text = "Nuevo Ingreso", color = Color.Black)
            }
            Button(
                onClick = onNavigateToNewExpense,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFCDD2))
            ) {
                Text(text = "Nuevo Gasto", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Ultimas Transacciones:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Lista de transacciones
        Column(modifier = Modifier.fillMaxWidth()) {
            uiState.recentTransactions.forEach { transaction ->
                TransactionItem(transaction = transaction)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onNavigateToTransactions) {
            Text(text = "Ver Más")
        }
    }
}