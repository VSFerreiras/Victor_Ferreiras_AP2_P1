package ucne.edu.Victor_Ferreiras_AP2_P1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ucne.edu.Victor_Ferreiras_AP2_P1.presentation.edit.EditScreen
import ucne.edu.Victor_Ferreiras_AP2_P1.presentation.list.ListScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.List.route
    ) {
        composable(Screens.List.route) {
            ListScreen(
                onAddClick = {
                    navController.navigate(Screens.Edit.route)
                },
                onEditClick = {
                    navController.navigate(Screens.Edit.route)
                }
            )
        }

        composable(Screens.Edit.route) {
            EditScreen(
                onSavedNavigateBack = {
                    navController.popBackStack(
                        route = Screens.List.route,
                        inclusive = false
                    )
                }
            )
        }
    }
}
