package com.example.appfinanzas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appfinanzas.model.Transaction
import com.example.appfinanzas.model.TransactionType
import java.text.NumberFormat
import java.util.Locale

// --- Item para la PANTALLA PRINCIPAL ---
@Composable
fun TransactionItem(transaction: Transaction) {
    val amountColor = if (transaction.type == TransactionType.EXPENSE) Color.Red else Color(0xFF006400)
    val formattedAmount = NumberFormat.getCurrencyInstance(Locale("es", "CL")).format(transaction.amount)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = transaction.detail, fontSize = 12.sp, color = Color.Gray)
            Text(text = transaction.description, fontSize = 16.sp)
        }
        Text(text = formattedAmount, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = amountColor)
    }
    Divider()
}

// --- Item para la PANTALLA DE TRANSACCIONES ---
@Composable
fun TransactionItemFullRow(transaction: Transaction) {
    val amountColor = if (transaction.type == TransactionType.EXPENSE) Color.Red else Color(0xFF006400)
    val formattedAmount = NumberFormat.getCurrencyInstance(Locale("es", "CL")).format(transaction.amount)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = transaction.detail, modifier = Modifier.weight(1f), fontSize = 14.sp)
        Text(text = transaction.description, modifier = Modifier.weight(1f), fontSize = 14.sp)
        Text(
            text = formattedAmount,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp,
            color = amountColor,
            fontWeight = FontWeight.Bold,
            textAlign = androidx.compose.ui.text.style.TextAlign.End
        )
    }
    Divider()
}