package ucne.edu.Victor_Ferreiras_AP2_P1.data.mappers

import ucne.edu.Victor_Ferreiras_AP2_P1.data.EntradaEntity
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.model.Entrada

fun EntradaEntity.toDomain(): Entrada {
    return Entrada(
        id = IdEntrada.toInt(),
        fecha = "",
        nombreCliente = NombreCliente,
        cantidad = Cantidad,
        precio = Precio.toDouble()
    )
}

fun Entrada.toEntity(): EntradaEntity {
    return EntradaEntity(
        IdEntrada = id.toLong(),
        NombreCliente = nombreCliente,
        Cantidad = cantidad,
        Precio = precio.toInt()
    )
}
