package mx.itesm.appdibujandounmanana.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.Correo
import mx.itesm.appdibujandounmanana.model.RecoveryData
import mx.itesm.appdibujandounmanana.API.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordViewModel : ViewModel() {

    val answerForgotPassword = MutableLiveData<String>()
    val answerRecoverPassword = MutableLiveData<String>()

    fun forgotPassword(correo: Correo){
        val call = RetrofitInstance.servicioCovidApi.olvidoContrasena(correo)
        //println(call.request().url.toString())
        println("omg")
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    answerForgotPassword.value = response.body()

                    //answer.value = response.body().toString()
                    if(answerForgotPassword.value=="YES") {
                        println("Funcionó")
                    }else{
                        println("Fallo")
                    }

                } else {
                    //answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    //println(answer.value)
                    println("No sesion :( 2")
                    answerForgotPassword.value = "NO"
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
                answerForgotPassword.value = "NO"
            }
        })
    }

    fun recoverPassword(recoveryData: RecoveryData){
        val call = RetrofitInstance.servicioCovidApi.recuperarContrasena(recoveryData)
        //println(call.request().url.toString())
        println("omg")
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    answerRecoverPassword.value = response.body()

                    //answer.value = response.body().toString()
                    if(answerRecoverPassword.value=="YES") {
                        println("Funcionó")
                    }else{
                        println("Fallo")
                    }

                } else {
                    //answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    //println(answer.value)
                    println("No sesion :( 2")
                    answerRecoverPassword.value = "NO"
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
                answerRecoverPassword.value = "NO"
            }
        })
    }
}