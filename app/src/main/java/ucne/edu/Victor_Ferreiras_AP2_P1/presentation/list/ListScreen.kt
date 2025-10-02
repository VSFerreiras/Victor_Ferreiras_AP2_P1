package ucne.edu.Victor_Ferreiras_AP2_P1.presentation.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onAddClick: () -> Unit,
    onEditClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var entradaToDelete by remember { mutableStateOf<Entrada?>(null) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        ListScreenContent(
            entradas = uiState.entradas,
            onEditClick = onEditClick,
            onDeleteClick = { entrada ->
                entradaToDelete = entrada
                showDeleteDialog = true
            },
            modifier = Modifier.fillMaxSize().padding(padding),
            padding = PaddingValues(8.dp)
        )

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDeleteDialog = false
                    entradaToDelete = null
                },
                title = {
                    Text("Confirmar eliminación")
                },
                text = {
                    Text("¿Estás seguro de que quieres eliminar el registro de ${entradaToDelete?.nombreCliente}?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            entradaToDelete?.id?.let { viewModel.onDelete(it) }
                            showDeleteDialog = false
                            entradaToDelete = null
                        }
                    ) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDeleteDialog = false
                            entradaToDelete = null
                        }
                    ) {
                        Text("Cancelar")
                    }
                },
                icon = {
                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "Advertencia",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            )
        }
    }
}

@Composable
fun ListScreenContent(
    entradas: List<Entrada>,
    onEditClick: (Int) -> Unit,
    onDeleteClick: (Entrada) -> Unit,
    modifier: Modifier = Modifier,
    padding: PaddingValues = PaddingValues()
) {
    Column(modifier = modifier) {
        Text(
            text = "Huacales",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        if (entradas.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No hay huacales registrados")
            }
            return
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = padding
        ) {
            items(entradas) { entrada ->
                EntradaCard(
                    entrada = entrada,
                    onEditClick = { onEditClick(entrada.id) },
                    onDeleteClick = { onDeleteClick(entrada) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun EntradaCard(
    entrada: Entrada,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = entrada.nombreCliente,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Fecha: ${entrada.fecha}",
                        style = MaterialTheme.typography.bodySmall
                    )

                    Text(
                        text = "Cant: ${entrada.cantidad}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Text(
                    text = "$${entrada.precio}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    val sampleList = listOf(
        Entrada(id = 1, fecha = "2025-10-01", nombreCliente = "Cliente 1", cantidad = 10, precio = 50.0),
        Entrada(id = 2, fecha = "2025-10-02", nombreCliente = "Cliente 2", cantidad = 5, precio = 25.0)
    )
    ListScreenContent(
        entradas = sampleList,
        onEditClick = {},
        onDeleteClick = {}
    )
}