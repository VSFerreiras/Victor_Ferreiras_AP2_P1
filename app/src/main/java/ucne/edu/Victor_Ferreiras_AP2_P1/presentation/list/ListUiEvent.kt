package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.list

import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada

sealed class ListUiEvent {
    data class DeleteEntrada(val entrada: Entrada) : ListUiEvent()
    data class NavigateToEdit(val entradaId: Int?) : ListUiEvent()
}
