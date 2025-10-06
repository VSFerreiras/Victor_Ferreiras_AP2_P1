package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    LaunchedEffect(Unit) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is EditUiEvent.Saved -> onSavedNavigateBack()
            }
        }
    }

    Scaffold { padding ->
        EditScreenContent(
            modifier = Modifier.padding(padding),
            entrada = uiState.entrada,
            errorMessage = uiState.error,
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
    errorMessage: String,
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

        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = androidx.compose.material3.MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        OutlinedTextField(
            value = entrada.fecha,
            onValueChange = onFechaChange,
            label = { Text("Fecha (DD-MM-YYYY)") },
            isError = errorMessage.contains("fecha", ignoreCase = true),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = entrada.nombreCliente,
            onValueChange = onNombreClienteChange,
            label = { Text("Nombre cliente") },
            isError = errorMessage.contains("nombre", ignoreCase = true) ||
                    errorMessage.contains("cliente", ignoreCase = true),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = if (entrada.cantidad == 0) "" else entrada.cantidad.toString(),
            onValueChange = onCantidadChange,
            label = { Text("Cantidad") },
            isError = errorMessage.contains("cantidad", ignoreCase = true),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = if (entrada.precio == 0.0) "" else entrada.precio.toString(),
            onValueChange = onPrecioChange,
            label = { Text("Precio") },
            isError = errorMessage.contains("precio", ignoreCase = true),
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
    }
}

@Preview(showBackground = true)
@Composable
fun EditScreenPreview() {
    val sample = Entrada(id = 0, fecha = "10-01-2025", nombreCliente = "Cliente Ejemplo", cantidad = 5, precio = 12.5)
    EditScreenContent(
        entrada = sample,
        errorMessage = "• Fecha requerida\n• Cliente requerido",
        saving = false,
        onFechaChange = {},
        onNombreClienteChange = {},
        onCantidadChange = {},
        onPrecioChange = {},
        onSave = {}
    )
}