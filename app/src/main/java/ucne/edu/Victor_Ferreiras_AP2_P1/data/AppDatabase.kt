package ucne.edu.Victor_Ferreiras_AP2_P1.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import ucne.edu.Victor_Ferreiras_AP2_P1.data.EntradaDao
import ucne.edu.Victor_Ferreiras_AP2_P1.data.EntradaEntity

@Database(
    entities = [EntradaEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entradaDao(): EntradaDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE EntradaHuacales ADD COLUMN Fecha TEXT DEFAULT ''")
            }
        }
    }
}