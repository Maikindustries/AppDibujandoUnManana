package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProjectData (
    @SerializedName("idProject")
    val id: Int,
    @SerializedName("ProjectName")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("RegistrationDate")
    val resgistrationDate: String,
    @SerializedName("approbed")
    val approved: Boolean
) : Serializable


data class JsonProjectData(
    @SerializedName("UserData")
    val project: ProjectData
) : Serializable