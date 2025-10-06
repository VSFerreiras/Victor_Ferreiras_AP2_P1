package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase.GetEntradaUseCase
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase.UpsertEntradaUseCase
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase.ValidateEntradaUseCase
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val getEntradaUseCase: GetEntradaUseCase,
    private val upsertEntradaUseCase: UpsertEntradaUseCase,
    private val validateEntradaUseCase: ValidateEntradaUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditUiState())
    val uiState: StateFlow<EditUiState> = _uiState

    private val _events = MutableSharedFlow<EditUiEvent>()
    val events = _events.asSharedFlow()

    init {
        val id = savedStateHandle.get<Int>("entradaId")
        if (id != null && id > 0) {
            viewModelScope.launch {
                val entrada = getEntradaUseCase(id)
                if (entrada != null) {
                    _uiState.value = _uiState.value.copy(entrada = entrada)
                }
            }
        }
    }

    fun onFechaChange(value: String) {
        _uiState.value = _uiState.value.copy(entrada = _uiState.value.entrada.copy(fecha = value))
    }

    fun onNombreClienteChange(value: String) {
        _uiState.value = _uiState.value.copy(entrada = _uiState.value.entrada.copy(nombreCliente = value))
    }

    fun onCantidadChange(value: String) {
        val intVal = value.toIntOrNull() ?: 0
        _uiState.value = _uiState.value.copy(entrada = _uiState.value.entrada.copy(cantidad = intVal))
    }

    fun onPrecioChange(value: String) {
        val doubleVal = value.toDoubleOrNull() ?: 0.0
        _uiState.value = _uiState.value.copy(entrada = _uiState.value.entrada.copy(precio = doubleVal))
    }

    fun onSave() {
        viewModelScope.launch {
            val entrada = _uiState.value.entrada
            val validation = validateEntradaUseCase(entrada)
            if (!validation.successful) {
                _uiState.value = _uiState.value.copy(
                    error = validation.errorMessages.joinToString("\n• ", "• ")
                )
                return@launch
            }
            _uiState.value = _uiState.value.copy(saving = true, error = "")
            upsertEntradaUseCase(entrada)
            _uiState.value = _uiState.value.copy(saving = false)
            _events.emit(EditUiEvent.Saved)
        }
    }
}