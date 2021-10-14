package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Correo (
    @SerializedName("email")
    val email: String
    ) : Serializable


data class JsonCorreo(
    @SerializedName("Correo")
    val correo: Correo
) : Serializable