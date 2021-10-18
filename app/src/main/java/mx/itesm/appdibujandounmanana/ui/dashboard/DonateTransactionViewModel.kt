package mx.itesm.appdibujandounmanana.ui.dashboard

import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.JsonDonacionData
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonateTransactionViewModel : ViewModel() {
    fun postDonacion(donacionData: JsonDonacionData){
        val call = RetrofitInstance.servicioCovidApi.registrarDonacion(donacionData)

        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
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