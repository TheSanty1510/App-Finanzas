package com.example.appfinanzas.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun SummaryBox(title: String, amount: Double, modifier: Modifier = Modifier) {
    // Formatear el número como moneda
    val formattedAmount = NumberFormat.getCurrencyInstance(Locale("es", "CL")).format(amount)

    Column(
        modifier = modifier
            .padding(4.dp)
            .border(1.dp, Color.Gray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 14.sp)
        Text(text = formattedAmount, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}