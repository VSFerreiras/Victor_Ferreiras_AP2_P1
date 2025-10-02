package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada

@Composable
fun EditScreen(
    viewModel: EditViewModel = hiltViewModel(),
    onSavedNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is EditUiEvent.Saved -> onSavedNavigateBack()
                is EditUiEvent.ShowError -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        EditScreenContent(
            modifier = Modifier.padding(padding),
            entrada = uiState.entrada,
            errorMessage = if (uiState.error.isBlank()) null else uiState.error,
            saving = uiState.saving,
            onFechaChange = viewModel::onFechaChange,
            onNombreClienteChange = viewModel::onNombreClienteChange,
            onCantidadChange = { viewModel.onCantidadChange(it) },
            onPrecioChange = { viewModel.onPrecioChange(it) },
            onSave = viewModel::onSave
        )
    }
}

@Composable
fun EditScreenContent(
    modifier: Modifier = Modifier,
    entrada: Entrada,
    errorMessage: String? = null,
    saving: Boolean = false,
    onFechaChange: (String) -> Unit,
    onNombreClienteChange: (String) -> Unit,
    onCantidadChange: (String) -> Unit,
    onPrecioChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registro de Huacales",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = entrada.fecha,
            onValueChange = onFechaChange,
            label = { Text("Fecha (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = entrada.nombreCliente,
            onValueChange = onNombreClienteChange,
            label = { Text("Nombre cliente") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = if (entrada.cantidad == 0) "" else entrada.cantidad.toString(),
            onValueChange = onCantidadChange,
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = if (entrada.precio == 0.0) "" else entrada.precio.toString(),
            onValueChange = onPrecioChange,
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onSave,
            enabled = !saving,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (saving) "Guardando..." else "Guardar")
        }
        errorMessage?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = it,
                color = androidx.compose.material3.MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    val sample = Entrada(id = 0, fecha = "2025-10-01", nombreCliente = "Cliente Ejemplo", cantidad = 5, precio = 12.5)
    EditScreenContent(
        entrada = sample,
        errorMessage = null,
        saving = false,
        onFechaChange = {},
        onNombreClienteChange = {},
        onCantidadChange = {},
        onPrecioChange = {},
        onSave = {}
    )
}