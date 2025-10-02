// File: app/src/main/java/ucne/edu/Victor_Ferreiras_AP2_P1/presentation/edit/EditUiState.kt
package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.edit

import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada

data class EditUiState(
    val entrada: Entrada = Entrada(),
    val error: String = "",
    val saving: Boolean = false
)
