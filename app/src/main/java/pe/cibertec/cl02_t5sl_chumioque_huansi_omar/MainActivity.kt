package pe.cibertec.cl02_t5sl_chumioque_huansi_omar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.ui.main.MainFragment


/// IGNORAR ESTE ACTIVITY
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}