package ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase

import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.repository.EntradaRepository
import java.util.regex.Pattern
import javax.inject.Inject

class ValidateEntradaUseCase @Inject constructor(
    private val repository: EntradaRepository
) {
    suspend operator fun invoke(entrada: Entrada): ValidationResult {
        if (entrada.fecha.isBlank()) {
            return ValidationResult(false, "Fecha requerida")
        }

        if (!isValidDateFormat(entrada.fecha)) {
            return ValidationResult(false, "Formato de fecha inválido. Use DD-MM-YYYY")
        }

        if (entrada.nombreCliente.isBlank()) {
            return ValidationResult(false, "Cliente requerido")
        }

        if (!isValidName(entrada.nombreCliente)) {
            return ValidationResult(false, "El nombre solo puede contener letras y espacios")
        }

        if (entrada.cantidad <= 0) {
            return ValidationResult(false, "La cantidad debe ser mayor que 0")
        }

        if (entrada.cantidad > 10000) {
            return ValidationResult(false, "La cantidad no puede exceder 10000")
        }

        if (entrada.precio < 0.0) {
            return ValidationResult(false, "El precio no puede ser negativo")
        }

        if (entrada.precio > 100000.0) {
            return ValidationResult(false, "El precio no puede exceder 100000")
        }

        val existingEntrada = repository.getByNombres(entrada.nombreCliente)
        if (existingEntrada != null && existingEntrada.id != entrada.id) {
            return ValidationResult(false, "Ya existe un cliente con este nombre")
        }

        return ValidationResult(true, "")
    }

    private fun isValidDateFormat(date: String): Boolean {
        val pattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$")
        return pattern.matcher(date).matches()
    }

    private fun isValidName(name: String): Boolean {
        return name.matches(Regex("^[a-zA-Z ]+$"))
    }
}

data class ValidationResult(val successful: Boolean, val errorMessage: String)