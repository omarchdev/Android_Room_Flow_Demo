package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.ui.editproducto

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.CL02App
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.R
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.databinding.FragmentEditProductoBinding
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.Producto


///Fragment para agregar y editar producto
class EditProductoFragment : Fragment() {

    private var _binding: FragmentEditProductoBinding? = null
    val binding get() = _binding!!
    var productoTemp=Producto("","",0)
    private val editProductoViewModel: EditProductoViewModel by viewModels {
        EditProductoViewModelFactory((activity?.application as CL02App).repository)
    }

    companion object {
        fun newInstance() = EditProductoFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditProductoBinding.inflate(inflater, container, false)

        return _binding!!.root
    }

    //Validar si los campos son obligatorios
    fun validar():Boolean{
        var permiteGuardar = true
        binding.txtCodigo.error = null
        binding.txtNombre.error = null
        binding.txtCantidad.error = null
        if (binding.txtCodigo.editText?.text.toString().trim() == "") {
            permiteGuardar = false
            binding.txtCodigo.error = getString(R.string.mensaje_txt_advertencia)
        }
        if (binding.txtNombre.editText?.text.toString().trim() == "") {
            permiteGuardar = false
            binding.txtNombre.error = getString(R.string.mensaje_txt_advertencia)
        }
        if (binding.txtCantidad.editText?.text.toString().trim() == "") {
            permiteGuardar = false
            binding.txtCantidad.error = getString(R.string.mensaje_txt_advertencia)
        }
        return permiteGuardar
    }


    fun clickGuardar() {
        val permiteGuardar=validar()
        if (permiteGuardar) {
            var cantidad = binding.txtCantidad.editText?.text.toString().trim().toInt()
            editProductoViewModel.insert(
                binding.txtCodigo.editText?.text.toString().trim(),
                binding.txtNombre.editText?.text.toString().trim(),
                cantidad,
            )
        } else {
            AlertDialog.Builder(context).setTitle(R.string.titulo_advertencia)
                .setMessage(R.string.mensaje_advertencia_2).setPositiveButton(
                    R.string.btnAceptarDialog
                ) { _, _ -> }.setOnDismissListener {
                }.create().show()
        }
    }

    fun  clickActualizar(){
        val permiteGuardar=validar()
        if (permiteGuardar) {
            var cantidad = binding.txtCantidad.editText?.text.toString().trim().toInt()
            editProductoViewModel.update(
                binding.txtCodigo.editText?.text.toString().trim(),
                binding.txtNombre.editText?.text.toString().trim(),
                cantidad,
            )
        } else {
            AlertDialog.Builder(context).setTitle(R.string.titulo_advertencia)
                .setMessage(R.string.mensaje_advertencia_2).setPositiveButton(
                    R.string.btnAceptarDialog
                ) { _, _ -> }.setOnDismissListener {
                }.create().show()
        }
    }
    fun clickEliminar(){
        editProductoViewModel.delete(productoTemp)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ////Ajustar pantalla si es producto nuevo o para actualizar
        val code = activity?.intent?.getStringExtra("CODE")
        if (code == "-----") {
            ///nuevo
           binding.txtCodigo.isEnabled=true
            binding.btnGrabar.visibility = View.VISIBLE
            binding.btnActualizar.visibility = View.GONE
            binding.btnEliminar.visibility = View.GONE
        } else {
            ///editar

            val name = activity?.intent?.getStringExtra("NAME")
            val qty = activity?.intent?.getIntExtra("QTY", 0)
            productoTemp= Producto(code!!,name!!,qty!!)
            binding.txtCodigo.editText?.setText(code!!)
            binding.txtNombre.editText?.setText(name!!)
            binding.txtCantidad.editText?.setText(qty!!.toString())
            //Para editar se bloquea el campo codigo para poder actualizar
            binding.txtCodigo.isEnabled=false
            binding.btnGrabar.visibility = View.GONE
            binding.btnActualizar.visibility = View.VISIBLE
            binding.btnEliminar.visibility = View.VISIBLE
        }

        //comportamiento para los click
        binding.btnGrabar.setOnClickListener {
            clickGuardar()
        }
        binding.btnEliminar.setOnClickListener {
            clickEliminar()
        }
        binding.btnActualizar.setOnClickListener {
            clickActualizar()
        }
        //////////////////////////////////

        //////////////////Mensajes de dialogo para cada confirmación
        editProductoViewModel.getResultProcesoGuardar().observe(viewLifecycleOwner) {
            if (it.codigo == 1) {
                AlertDialog.Builder(context).setTitle(R.string.titulo_confirmacion)
                    .setMessage(R.string.mensaje_confirmacion_1).setPositiveButton(
                        R.string.btnAceptarDialog
                    ) { _, _ -> }.setOnDismissListener {
                        activity?.finish()
                    }.create().show()
            }
            if (it.codigo == 2) {
                AlertDialog.Builder(context).setTitle(R.string.titulo_advertencia)
                    .setMessage(R.string.mensaje_advertencia_1).setPositiveButton(
                        R.string.btnAceptarDialog
                    ) { _, _ -> }
                    .setOnDismissListener { editProductoViewModel.reiniciarProcesoGuardar() }
                    .create().show()
            }
            if (it.codigo == 3) {
                AlertDialog.Builder(context).setTitle(R.string.titulo_confirmacion)
                    .setMessage(R.string.mensaje_confirmacion_2).setPositiveButton(
                        R.string.btnAceptarDialog
                    ) { _, _ -> }.setOnDismissListener {
                        activity?.finish()
                    }.create().show()
            }
            if (it.codigo == 5) {
                AlertDialog.Builder(context).setTitle(R.string.titulo_confirmacion)
                    .setMessage(R.string.mensaje_confirmacion_5).setPositiveButton(
                        R.string.btnAceptarDialog
                    ) { _, _ -> }.setOnDismissListener {
                        activity?.finish()
                    }.create().show()
            }
        }

    }
}