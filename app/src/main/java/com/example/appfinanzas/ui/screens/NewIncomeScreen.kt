package com.example.appfinanzas.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.appfinanzas.viewmodel.NewTransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewIncomeScreen(
    viewModel: NewTransactionViewModel,
    onBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // Categorías de ejemplo
    val categories = listOf("Sueldo", "Transferencia", "Préstamo", "Otro")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Nuevo Ingreso", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.monto,
            onValueChange = viewModel::onMontoChanged,
            label = { Text("Monto") },
            placeholder = { Text("\$xx.xxx") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.descripcion,
            onValueChange = viewModel::onDescripcionChanged,
            label = { Text("Descripción") },
            placeholder = { Text("XXXXXXXX") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Simulación de Dropdown (Menú desplegable)
        var expanded by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = uiState.categoria.ifEmpty { "Seleccionar Categoría" },
                onValueChange = {},
                label = { Text("Categoría") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category) },
                        onClick = {
                            viewModel.onCategoriaChanged(category)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f)) // Empuja los botones hacia abajo

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFBE9E7))
            ) {
                Text(text = "Cancelar", color = Color.Black)
            }
            Button(onClick = {
                viewModel.saveTransaction()
                onBack() // Vuelve atrás después de guardar
            }) {
                Text(text = "Guardar")
            }
        }
    }
}