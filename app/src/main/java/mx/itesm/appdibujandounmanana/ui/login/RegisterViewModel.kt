package mx.itesm.appdibujandounmanana.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.appdibujandounmanana.model.JsonOrganizationData
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import mx.itesm.appdibujandounmanana.model.JsonUserData
import mx.itesm.appdibujandounmanana.model.OrganizationData
import mx.itesm.appdibujandounmanana.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    val userAnswer = MutableLiveData<String>()
    val organizationAnswer = MutableLiveData<String>()

    fun userRegister(user: UserData) {
        println(Gson().toJson(JsonUserData(user)))

        val call = RetrofitInstance.servicioCovidApi.agregarUsuario(JsonUserData(user))
        //println(call.request().url.toString())
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                if (response.isSuccessful) {
                    userAnswer.value = response.body().toString()
                    if(userAnswer.value=="YES"){
                        println("Registro bien hecho")

                    }else{
                        println("No :(")
                    }
                } else {
                    userAnswer.value = "Error [${response.code()}] ${response.errorBody()}"
                    println(userAnswer.value)
                    println("No :( 2")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                userAnswer.value = "Error, ${t.localizedMessage}"
                println(userAnswer.value)
                println("No :( 3")
            }
        })
    }

    fun organizationRegister(user: OrganizationData) {
        println(Gson().toJson(JsonOrganizationData(user)))

        val call = RetrofitInstance.servicioCovidApi.agregarOrganizacion(JsonOrganizationData(user))
        //println(call.request().url.toString())
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                if (response.isSuccessful) {
                    organizationAnswer.value = response.body().toString()
                    if(organizationAnswer.value=="NO"){
                        println("No :(")

                    }else{
                        println("Register is succesfull")
                    }
                } else {
                    organizationAnswer.value = "Error [${response.code()}] ${response.errorBody()}"
                    println(userAnswer.value)
                    println("No :( 2")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                organizationAnswer.value = "Error, ${t.localizedMessage}"
                println(organizationAnswer.value)
                println("No :( 3")
            }
        })
    }
}