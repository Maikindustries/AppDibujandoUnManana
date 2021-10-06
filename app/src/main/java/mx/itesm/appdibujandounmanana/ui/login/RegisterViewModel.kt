package mx.itesm.appdibujandounmanana.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import mx.itesm.appdibujandounmanana.model.JsonUserData
import mx.itesm.appdibujandounmanana.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    val respuesta = MutableLiveData<String>()

    fun registrarUsuario(user: UserData) {
        println(Gson().toJson(JsonUserData(user)))
        val call = RetrofitInstance.servicioCovidApi.agregarUsuario(JsonUserData(user))
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    respuesta.value = response.body().toString()
                    if(respuesta.value=="YES"){
                        println("Registro bien hecho")
                    }else{
                        println("No :(")
                    }
                } else {
                    respuesta.value = "Error [${response.code()}] ${response.errorBody()}"
                    println(respuesta.value)
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                respuesta.value = "Error, ${t.localizedMessage}"
                println(respuesta.value)
            }
        })
    }
}