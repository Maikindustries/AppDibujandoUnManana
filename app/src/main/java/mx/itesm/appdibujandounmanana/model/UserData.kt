package mx.rmr.enviadatos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserData (
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("salt")
    val salt: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("hasMonthlyDonation")
    val hasMonthlyDonation: Boolean
    ) : Serializable


data class JsonUserData(
    @SerializedName("UserData")
    val alumno: UserData
) : Serializable