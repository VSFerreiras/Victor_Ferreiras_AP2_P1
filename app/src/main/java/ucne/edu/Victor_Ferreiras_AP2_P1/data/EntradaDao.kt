package ucne.edu.Victor_Ferreiras_AP2_P1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface EntradaDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(EntradaHuacales: EntradaEntity): Long

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun update(EntradaHuacales: EntradaEntity)

    @Delete
    suspend fun delete(EntradaHuacales: EntradaEntity)

    @Query("SELECT * FROM EntradaHuacales WHERE LOWER(NombreCliente) = LOWER(:nombres) LIMIT 1")
    suspend fun getByNombres(nombres: String): EntradaEntity?

    @Query("SELECT * FROM EntradaHuacales ORDER BY NombreCliente ASC")
    fun observeAll(): Flow<List<EntradaEntity>>
}