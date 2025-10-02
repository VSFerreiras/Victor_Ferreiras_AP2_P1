package ucne.edu.Victor_Ferreiras_AP2_P1.navigation

sealed class Screens(val route: String) {
    object List : Screens("list")
    object Edit : Screens("edit?entradaId={entradaId}") {
        const val base = "edit"
        fun createRoute(entradaId: Int?) = if (entradaId == null) "$base?entradaId=" else "$base?entradaId=$entradaId"
    }
}
