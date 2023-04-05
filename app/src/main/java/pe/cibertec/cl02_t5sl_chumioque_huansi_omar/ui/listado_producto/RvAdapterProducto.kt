package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.ui.listado_producto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.R
import pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model.Producto

interface OnItemClickListener{
    fun onItemClick(producto: Producto)
}

class RvAdapterProducto : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list: ArrayList<Producto> = ArrayList()
    var onItemClickListener:OnItemClickListener?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v= LayoutInflater.from(parent.context).inflate(R.layout.item_rv_producto,parent,false)
        return ProductoVH(v)
    }

    inner class ProductoVH(item: View):RecyclerView.ViewHolder(item){
        val contentItemProducto:ConstraintLayout=itemView.findViewById(R.id.contentItemProducto)
         val lblCodigo: TextView = itemView.findViewById(R.id.lblCodigo)
         val lblNombre: TextView = itemView.findViewById(R.id.lblNombre)
         val lblCantidad: TextView = itemView.findViewById(R.id.lblCantidad)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val h=holder as ProductoVH

        h.lblCodigo.text = list[position].codigo
        h.lblNombre.text = list[position].nombre
        h.lblCantidad.text = list[position].cantidad.toString()
        h.contentItemProducto.setOnClickListener { onItemClickListener?.onItemClick(list.get(position)) }
    }

    fun cargarLista(list1: ArrayList<Producto>) {
        list.clear()
        list.addAll(list1)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}