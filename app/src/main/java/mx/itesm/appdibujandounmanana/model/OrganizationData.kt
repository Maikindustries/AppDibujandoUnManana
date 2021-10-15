package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class OrganizationData (
    @SerializedName("name")
    val nombre: String,
    val tag: String,
    val description: String,
    val password: String,
    @SerializedName("phoneNumber")
    val phone: String,
    val email: String) : Serializable

data class JsonOrganizationData(
    @SerializedName("UserOrganizacion")
    val organization: OrganizationData
): Serializable