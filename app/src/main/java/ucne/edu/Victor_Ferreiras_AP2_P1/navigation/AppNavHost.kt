package ucne.edu.Victor_Ferreiras_AP2_P1.navigation

import androidx.compose.runtime.Composable
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
                    navController.navigate(Screens.Edit.createRoute())
                },
                onEditClick = { entradaId ->
                    navController.navigate(Screens.Edit.createRoute(entradaId))
                }
            )
        }

        composable(Screens.Edit.createRoute()) {
            EditScreen(
                onSavedNavigateBack = {
                    navController.popBackStack(
                        route = Screens.List.route,
                        inclusive = false
                    )
                }
            )
        }

        composable(
            route = "edit/{entradaId}",
            arguments = listOf(navArgument("entradaId") { type = NavType.IntType })
        ) { backStackEntry ->
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