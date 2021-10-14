package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

// Clase especial, genera toString, get/set, equals,
data class Proyecto (
    @SerializedName("Nombre")
    val nombre: String,
    @SerializedName("Estado")
    val estado: Boolean
    ) : Serializable // Pasara entre fragmentos
