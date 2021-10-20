package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecoveryData(
    @SerializedName("email")
    val email: String,
    @SerializedName("codigo")
    val code: String
): Serializable