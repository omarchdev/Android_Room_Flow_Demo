package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.repository

import kotlinx.coroutines.flow.Flow
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.dao.ProductoDao
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.Producto

class ProductoRepo(private val productoDao: ProductoDao) {

    val getAll: Flow<List<Producto>> = productoDao.getAll()


    fun insert(producto: Producto){
        productoDao.insert(producto)
    }

    fun getProducto(code:String):List<Producto>{
       return productoDao.getById(code)
    }
    fun updateProducto(producto: Producto){
        return productoDao.update(producto)
    }

    fun deleteProducto(producto: Producto){
        return productoDao.delete(producto)
    }

}