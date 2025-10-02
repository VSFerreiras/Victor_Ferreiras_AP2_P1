package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.list

import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada

data class ListUiState(
    val entradas: List<Entrada> = emptyList(),
    val loading: Boolean = false
)
