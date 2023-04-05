package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.dao.ProductoDao
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.Producto

@Database(entities = [Producto::class], version = 1)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun productoDao():ProductoDao

    companion object{
        // Singlenton para iniciar la base de datos
        @Volatile
        private var INSTANCE:AppDatabase?=null
        fun getDatabase(context: Context):AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance=Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,"cl02_db").build()
                INSTANCE=instance
                instance
            }
        }
    }
}