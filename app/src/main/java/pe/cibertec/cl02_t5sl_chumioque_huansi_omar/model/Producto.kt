package pe.cibertec.cl02_t5sl_chumioque_huansi_omar.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Producto (
    @PrimaryKey @ColumnInfo(name = "code") val codigo :String,
    @ColumnInfo(name = "name") val nombre :String,
    @ColumnInfo(name = "quantity")   val cantidad :Int,
)