package ucne.edu.Victor_Ferreiras_AP2_P1.domain.usecase

import kotlinx.coroutines.flow.Flow
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.repository.EntradaRepository
import javax.inject.Inject

class ObserveEntradasUseCase @Inject constructor(
    private val repository: EntradaRepository
) {
    operator fun invoke(): Flow<List<Entrada>> {
        return repository.observeAll()
    }
}
