package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrganizacionInicioSesion (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
) : Serializable


data class JsonOrganizacionInicioSesion(
    @SerializedName("UserInicioSesion")
    val inicioSesion: OrganizacionInicioSesion
) : Serializable