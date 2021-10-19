package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CodigoData(
    @SerializedName("codigo")
    val codigo: String
): Serializable