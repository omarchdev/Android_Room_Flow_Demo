package pe.cibertec.cl02_t5sl_chumioque_huansi_omar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.ui.listado_producto.ListadoProductoFragment


//La aplicación inicia con esta actividad


class ListadoProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_producto)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListadoProductoFragment.newInstance())
                .commitNow()
        }
    }
}