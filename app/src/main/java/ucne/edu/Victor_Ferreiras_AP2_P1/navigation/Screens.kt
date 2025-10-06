package ucne.edu.Victor_Ferreiras_AP2_P1.navigation

sealed class Screens(val route: String) {
    object List : Screens("list")
    object Edit : Screens("edit/{entradaId}") {
        fun createRoute(entradaId: Int? = null) = if (entradaId == null) "edit" else "edit/$entradaId"
    }
}