package mx.itesm.appdibujandounmanana.model

import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("answer")
    val answer: String
)