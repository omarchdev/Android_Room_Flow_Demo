package pe.cibertec.cl02_t5sl_chumioque_huansi_omar

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.repository.ProductoRepo
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.room.AppDatabase

class CL02App:Application() {
    val database by lazy{ AppDatabase.getDatabase(this)}
    val repository by lazy{ ProductoRepo(database.productoDao()) }
}