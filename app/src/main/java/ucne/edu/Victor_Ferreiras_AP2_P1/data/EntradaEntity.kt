package ucne.edu.Victor_Ferreiras_AP2_P1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EntradaHuacales")
data class EntradaEntity(
    @PrimaryKey(autoGenerate = true)
    val IdEntrada: Long = 0,
    val Fecha: String = "",
    val NombreCliente: String = "",
    val Cantidad: Int = 0,
    val Precio: Int = 0
)