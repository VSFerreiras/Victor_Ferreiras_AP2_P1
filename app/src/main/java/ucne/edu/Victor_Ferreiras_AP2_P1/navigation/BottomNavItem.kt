package ucne.edu.Victor_Ferreiras_AP2_P1.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(val route: String, val icon: ImageVector, val title: String)

val bottomNavItems = listOf(
    BottomNavItem(Screens.List.route, Icons.Default.List, "Lista"),
    BottomNavItem(Screens.Edit.route, Icons.Default.Edit, "Editar")
)
