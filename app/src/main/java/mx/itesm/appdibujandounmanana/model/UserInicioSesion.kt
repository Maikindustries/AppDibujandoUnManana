package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInicioSesion (
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
    ) : Serializable


data class JsonInicioSesion(
    @SerializedName("UserInicioSesion")
    val inicioSesion: UserInicioSesion
) : Serializable