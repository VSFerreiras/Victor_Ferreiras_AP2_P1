package ucne.edu.Victor_Ferreiras_AP2_P1.domain.repository

import kotlinx.coroutines.flow.Flow
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada

interface EntradaRepository {
    suspend fun upsert(entrada: Entrada)
    fun observeAll(): Flow<List<Entrada>>
    suspend fun getById(id: Int): Entrada?
    suspend fun delete(entrada: Entrada)
}
