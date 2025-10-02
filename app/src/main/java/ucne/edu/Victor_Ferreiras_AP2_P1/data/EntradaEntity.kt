package ucne.edu.Victor_Ferreiras_AP2_P1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "EntradaHuacales", indices = [Index(value = ["NombreCliente"], unique = true)])
data class EntradaEntity(
    @PrimaryKey(autoGenerate = true) val IdEntrada: Long = 0L,
    val NombreCliente: String,
    val Cantidad: Int,
    val Precio: Int
)