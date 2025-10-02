package ucne.edu.Victor_Ferreiras_AP2_P1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ucne.edu.Victor_Ferreiras_AP2_P1.data.EntradaDao
import ucne.edu.Victor_Ferreiras_AP2_P1.data.EntradaEntity

@Database(entities = [EntradaEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entradaDao(): EntradaDao
}
