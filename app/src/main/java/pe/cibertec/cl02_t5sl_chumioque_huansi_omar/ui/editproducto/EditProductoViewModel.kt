package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.ui.editproducto

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.Producto
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.ResultProceso
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.repository.ProductoRepo

class EditProductoViewModel(private val repository: ProductoRepo) : ViewModel() {

    private val resultProcesoGuardar: MutableLiveData<ResultProceso> = MutableLiveData()

    fun getResultProcesoGuardar(): MutableLiveData<ResultProceso> {
        return resultProcesoGuardar
    }
    fun reiniciarProcesoGuardar() {
        val result = ResultProceso(0, "")
        resultProcesoGuardar.postValue(result)
    }
    fun update(codigo: String, nombre: String, cantidad: Int) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val producto = Producto(codigo, nombre, cantidad)
                var r = repository.updateProducto(producto)
                withContext(Dispatchers.Default) {
                    val result = ResultProceso(3, "")
                    resultProcesoGuardar.postValue(result)
                }
            }
        } catch (ex: java.lang.Exception) {
            Log.d("error1", ex.toString())
        }

    }

    fun delete(producto: Producto) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteProducto(producto)
            withContext(Dispatchers.Default) {
                val result = ResultProceso(5, "")
                resultProcesoGuardar.postValue(result)
            }
        }
    }

    fun insert(codigo: String, nombre: String, cantidad: Int) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val producto = Producto(codigo, nombre, cantidad)
                val productoBusqueda = repository.getProducto(codigo)
                if (productoBusqueda.isEmpty()) {
                    var r = repository.insert(producto)
                    withContext(Dispatchers.Default) {
                        val result = ResultProceso(1, "")
                        resultProcesoGuardar.postValue(result)
                    }
                } else {
                    val result = ResultProceso(2, codigo)
                    resultProcesoGuardar.postValue(result)
                }
            }
        } catch (ex: java.lang.Exception) {
            Log.d("error1", ex.toString())
        }
    }
}

class EditProductoViewModelFactory(private val repository: ProductoRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProductoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST1")
            return EditProductoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}