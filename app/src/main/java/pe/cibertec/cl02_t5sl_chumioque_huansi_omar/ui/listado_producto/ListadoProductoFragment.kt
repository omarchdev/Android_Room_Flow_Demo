package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.ui.listado_producto

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.CL02App
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.EditProductoActivity
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.databinding.FragmentListadoProductoBinding
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.Producto


class ListadoProductoFragment : Fragment() {
    val rvAdapterProducto=RvAdapterProducto()
    private var _binding: FragmentListadoProductoBinding? = null
    val binding get() = _binding!!

    companion object {
        fun newInstance() = ListadoProductoFragment()
    }


    private val listadoProductoViewModel: ListadoProductoViewModel by viewModels {
        ListadoProductoViewModelFactory((activity?.application as CL02App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListadoProductoBinding.inflate(inflater, container, false)
        binding.btnAgregarProducto.setOnClickListener {
            Intent(context, EditProductoActivity::class.java).apply {
                this.putExtra("CODE","-----")
                startActivity(this)
            }
        }
        //Adapter para recycler view
        binding.rvProductos.layoutManager=LinearLayoutManager(context)
        binding.rvProductos.adapter=rvAdapterProducto
        val dividerItemDecoration = DividerItemDecoration(
            binding.rvProductos.context,
            LinearLayoutManager(context).orientation
        )
        binding.rvProductos.addItemDecoration(dividerItemDecoration)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvAdapterProducto.onItemClickListener= object : OnItemClickListener {
        //captura el producto y se lo envia a editar
            override fun onItemClick(producto: Producto) {
                Intent(context, EditProductoActivity::class.java).apply {
                    this.putExtra("CODE",producto.codigo)
                    this.putExtra("NAME",producto.nombre)
                    this.putExtra("QTY",producto.cantidad)
                    startActivity(this)
                }
            }
        }

        // Observe para cargar los productos al recycler view
        listadoProductoViewModel.allProducts.observe(viewLifecycleOwner) {
               rvAdapterProducto.cargarLista(ArrayList(it))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}