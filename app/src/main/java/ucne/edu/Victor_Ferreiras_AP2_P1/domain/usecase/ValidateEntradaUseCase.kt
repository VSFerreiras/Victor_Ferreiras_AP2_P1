package ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase

import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada
import javax.inject.Inject

class ValidateEntradaUseCase @Inject constructor() {
    operator fun invoke(entrada: Entrada): ValidationResult {
        if (entrada.fecha.isBlank()) return ValidationResult(false, "Fecha requerida")
        if (entrada.nombreCliente.isBlank()) return ValidationResult(false, "Cliente requerido")
        if (entrada.cantidad <= 0) return ValidationResult(false, "Cantidad debe ser mayor que 0")
        if (entrada.precio < 0.0) return ValidationResult(false, "Precio inválido")
        return ValidationResult(true, "")
    }
}

data class ValidationResult(val successful: Boolean, val errorMessage: String)
