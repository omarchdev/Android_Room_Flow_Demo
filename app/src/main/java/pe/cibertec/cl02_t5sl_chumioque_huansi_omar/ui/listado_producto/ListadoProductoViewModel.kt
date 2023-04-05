package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.ui.listado_producto

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.Producto
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.repository.ProductoRepo

class ListadoProductoViewModel(repository:ProductoRepo ) : ViewModel() {

 val allProducts:LiveData<List<Producto>> = repository.getAll.asLiveData()

}

class ListadoProductoViewModelFactory(private val repository: ProductoRepo): ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(ListadoProductoViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ListadoProductoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}