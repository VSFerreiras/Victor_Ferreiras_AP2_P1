package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.edit

sealed class EditUiEvent {
    object Saved : EditUiEvent()
    data class ShowError(val message: String) : EditUiEvent()
}
