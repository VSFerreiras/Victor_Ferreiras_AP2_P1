package ucne.edu.Victor_Ferreiras_AP2_P1.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ucne.edu.Victor_Ferreiras_AP2_P1.data.EntradaDao
import ucne.edu.Victor_Ferreiras_AP2_P1.data.mappers.toDomain
import ucne.edu.Victor_Ferreiras_AP2_P1.data.mappers.toEntity
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.repository.EntradaRepository
import javax.inject.Inject

class EntradaRepositoryImpl @Inject constructor(
    private val dao: EntradaDao
) : EntradaRepository {
    override suspend fun upsert(entrada: Entrada) {
        if (entrada.id == 0) {
            dao.insert(entrada.toEntity())
        } else {
            dao.update(entrada.toEntity())
        }
    }

    override fun observeAll(): Flow<List<Entrada>> {
        return dao.observeAll().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getById(id: Int): Entrada? {
        return dao.getById(id.toLong())?.toDomain()
    }

    override suspend fun getByNombres(nombreCliente: String): Entrada? {
        return dao.getByNombres(nombreCliente)?.toDomain()
    }

    override suspend fun delete(entrada: Entrada) {
        dao.delete(entrada.toEntity())
    }
}