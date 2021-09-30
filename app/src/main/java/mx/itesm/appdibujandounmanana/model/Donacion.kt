package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// Clase especial, genera toString, get/set, equals,
data class Donacion (
    @SerializedName("fecha")
    val fecha: String,
    @SerializedName("monto")
    val monto: Float
    ) : Serializable // Pasara entre fragmentos
