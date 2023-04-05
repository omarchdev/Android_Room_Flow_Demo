package pe.cibertec.cl02_t5sl_chumioque_huansi_omar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.ui.editproducto.EditProductoFragment


//Activity para editar producto

class EditProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_producto)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EditProductoFragment.newInstance())
                .commitNow()

            }
        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val code= intent?.getStringExtra("CODE")
        //Se le cambia el titulo si es nuevo o para editar
        if(code=="-----"){
            this.supportActionBar!!.title=getString(R.string.titulo_producto_nuevo)
        }else{
            this.supportActionBar!!.title=getString(R.string.titulo_producto_editar)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}