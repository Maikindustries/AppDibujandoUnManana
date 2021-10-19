package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class NameData (
    @SerializedName("email")
    val email: String
) : Serializable