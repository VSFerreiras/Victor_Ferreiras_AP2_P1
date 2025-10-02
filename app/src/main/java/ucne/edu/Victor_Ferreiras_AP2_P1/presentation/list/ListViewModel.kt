package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase.DeleteEntradaUseCase
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase.ObserveEntradasUseCase
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    observeEntradasUseCase: ObserveEntradasUseCase,
    private val deleteEntradaUseCase: DeleteEntradaUseCase
) : ViewModel() {
    private val _events = MutableSharedFlow<ListUiEvent>()
    val events = _events.asSharedFlow()

    val uiState = observeEntradasUseCase()
        .map { ListUiState(entradas = it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ListUiState())

    fun onDelete(entradaId: Int) {
        viewModelScope.launch {
            val entrada = uiState.value.entradas.find { it.id == entradaId } ?: return@launch
            deleteEntradaUseCase(entrada)
        }
    }

    fun onAddNew() {
        viewModelScope.launch {
            _events.emit(ListUiEvent.NavigateToEdit(null))
        }
    }

    fun onEdit(entradaId: Int) {
        viewModelScope.launch {
            _events.emit(ListUiEvent.NavigateToEdit(entradaId))
        }
    }
}
