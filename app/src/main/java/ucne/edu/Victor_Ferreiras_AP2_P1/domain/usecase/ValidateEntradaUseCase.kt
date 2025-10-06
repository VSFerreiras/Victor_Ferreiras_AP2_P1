package ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase

import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.repository.EntradaRepository
import java.util.regex.Pattern
import javax.inject.Inject

class ValidateEntradaUseCase @Inject constructor(
    private val repository: EntradaRepository
) {
    suspend operator fun invoke(entrada: Entrada): ValidationResult {
        val errors = mutableListOf<String>()

        if (entrada.fecha.isBlank()) {
            errors.add("Fecha requerida")
        } else if (!isValidDateFormat(entrada.fecha)) {
            errors.add("Formato de fecha inválido. Use DD-MM-YYYY")
        }

        if (entrada.nombreCliente.isBlank()) {
            errors.add("Cliente requerido")
        } else if (!isValidName(entrada.nombreCliente)) {
            errors.add("El nombre solo puede contener letras y espacios")
        }

        if (entrada.cantidad <= 0) {
            errors.add("La cantidad debe ser mayor que 0")
        } else if (entrada.cantidad > 10000) {
            errors.add("La cantidad no puede exceder 10000")
        }

        if (entrada.precio < 0.0) {
            errors.add("El precio no puede ser negativo")
        } else if (entrada.precio > 100000.0) {
            errors.add("El precio no puede exceder 100000")
        }

        val existingEntrada = repository.getByNombres(entrada.nombreCliente)
        if (existingEntrada != null && existingEntrada.id != entrada.id) {
            errors.add("Ya existe un cliente con este nombre")
        }

        return ValidationResult(
            successful = errors.isEmpty(),
            errorMessages = if (errors.isNotEmpty()) errors else emptyList()
        )
    }

    private fun isValidDateFormat(date: String): Boolean {
        val pattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$")
        return pattern.matcher(date).matches()
    }

    private fun isValidName(name: String): Boolean {
        return name.matches(Regex("^[a-zA-Z ]+$"))
    }
}

data class ValidationResult(val successful: Boolean, val errorMessages: List<String>)