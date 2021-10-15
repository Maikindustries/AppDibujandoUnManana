package mx.itesm.appdibujandounmanana.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.appdibujandounmanana.model.*
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val answer = MutableLiveData<String>()
    val organizationAnswer = MutableLiveData<String>()

    fun userLogIn(user: UserInicioSesion) {
        println(Gson().toJson(JsonInicioSesion(user)))

        val call = RetrofitInstance.servicioCovidApi.iniciarSesion(JsonInicioSesion(user))
        //println(call.request().url.toString())
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                if (response.isSuccessful) {
                    answer.value = response.body().toString()
                    /*if(answer.value=="SIADMIN") {
                        println("admin")
                    }else if(answer.value=="SINORMAL"){
                        println("normal")
                    }else if(answer.value=="NOEXIST") {
                        println("no existe")
                    }else if(answer.value=="NO"){
                        println("No sesion :(")
                    }*/
                } else {
                    answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    println(answer.value)
                    println("No sesion :( 2")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                answer.value = "Error, ${t.localizedMessage}"
                println(answer.value)
                println("No sesion :( 3")
            }
        })
    }

    fun organizationLogIn(user: OrganizacionInicioSesion) {
        println(Gson().toJson(JsonOrganizacionInicioSesion(user)))

        val call = RetrofitInstance.servicioCovidApi.iniciarSesionOrganizacion(JsonOrganizacionInicioSesion(user))
        //println(call.request().url.toString())
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {

                if (response.isSuccessful) {
                    answer.value = response.body().toString()
                    /*if(answer.value=="SIADMIN") {
                        println("admin")
                    }else if(answer.value=="SINORMAL"){
                        println("normal")
                    }else if(answer.value=="NOEXIST") {
                        println("no existe")
                    }else if(answer.value=="NO"){
                        println("No sesion :(")
                    }*/
                } else {
                    answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    println(answer.value)
                    println("No sesion :( 2")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                answer.value = "Error, ${t.localizedMessage}"
                println(answer.value)
                println("No sesion :( 3")
            }
        })
    }
}