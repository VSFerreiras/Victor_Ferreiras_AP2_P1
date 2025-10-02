package ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase

import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.repository.EntradaRepository
import javax.inject.Inject

class GetEntradaUseCase @Inject constructor(
    private val repository: EntradaRepository
) {
    suspend operator fun invoke(id: Int): Entrada? {
        return repository.getById(id)
    }
}
