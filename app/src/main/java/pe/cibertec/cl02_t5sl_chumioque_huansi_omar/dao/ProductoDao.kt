package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.Producto
// Metodos para la base de datosd
@Dao
interface ProductoDao {

    @Query("Select * from producto order by code")
    fun getAll(): Flow<List<Producto>>

    @Query("Select * from producto where code=:code")
    fun getById(code: String): List<Producto>

    @Insert
    fun insert(vararg producto: Producto)

    @Delete
    fun delete(producto: Producto)

    @Update
    fun update(producto: Producto)
}