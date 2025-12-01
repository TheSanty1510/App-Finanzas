package com.example.appfinanzas.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

// Define todas las rutas de la app
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Transactions : Screen("transactions")
    object NewIncome : Screen("new_income")
    object NewExpense : Screen("new_expense")
}

// Define solo los items que van en la barra de navegación inferior
data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem(
        label = "Inicio",
        icon = Icons.Filled.Home,
        route = Screen.Home.route
    ),
    BottomNavItem(
        label = "Transacciones",
        icon = Icons.Filled.List,
        route = Screen.Transactions.route
    )
)