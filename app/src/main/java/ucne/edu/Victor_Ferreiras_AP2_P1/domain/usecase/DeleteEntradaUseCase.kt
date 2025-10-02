package ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase

import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.repository.EntradaRepository
import javax.inject.Inject

class DeleteEntradaUseCase @Inject constructor(
    private val repository: EntradaRepository
) {
    suspend operator fun invoke(entrada: Entrada) {
        repository.delete(entrada)
    }
}
