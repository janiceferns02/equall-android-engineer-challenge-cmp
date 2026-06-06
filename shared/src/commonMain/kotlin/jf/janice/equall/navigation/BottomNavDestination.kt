package jf.janice.equall.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    Home("home", "Home", Icons.Filled.Home),
    Loans("loans", "Loans", Icons.Filled.CurrencyRupee),
    Explore("explore", "Explore", Icons.Filled.AutoAwesome),
    Profile("profile", "Profile", Icons.Filled.AccountCircle),
    ;

    companion object {
        fun fromRoute(route: String?): BottomNavDestination? =
            entries.firstOrNull { it.route == route }
    }
}
