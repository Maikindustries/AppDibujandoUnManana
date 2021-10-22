package mx.itesm.appdibujandounmanana.ViewModel

import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.JsonDonacionData
import mx.itesm.appdibujandounmanana.API.RetrofitInstance
import mx.itesm.appdibujandounmanana.model.DonacionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInDonateViewModel : ViewModel() {
    fun postDonacion(donacionData: DonacionData){
        val call = RetrofitInstance.servicioCovidApi.registrarDonacion(donacionData)

        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                println(response.body())
                if (response.isSuccessful) {
                    println("Registro de Donacion exitosos.")
                } else {
                    println("Error [${response.code()}] ${response.errorBody()}")

                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                println("Error, ${t.localizedMessage}")
            }
        })
    }
}