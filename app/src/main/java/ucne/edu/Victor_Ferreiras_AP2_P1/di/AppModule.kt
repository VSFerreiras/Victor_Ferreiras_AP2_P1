package ucne.edu.Victor_Ferreiras_AP2_P1.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ucne.edu.Victor_Ferreiras_AP2_P1.data.EntradaDao
import ucne.edu.Victor_Ferreiras_AP2_P1.data.db.AppDatabase
import ucne.edu.Victor_Ferreiras_AP2_P1.data.repository.EntradaRepositoryImpl
import ucne.edu.Victor_Ferreiras_AP2_P1.domain.repository.EntradaRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
        }

        @Provides
        fun provideEntradaDao(db: AppDatabase): EntradaDao {
            return db.entradaDao()
        }
    }

    @Binds
    abstract fun bindEntradaRepository(impl: EntradaRepositoryImpl): EntradaRepository
}
