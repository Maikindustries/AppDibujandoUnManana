package mx.itesm.appdibujandounmanana.ui.mydonations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.appdibujandounmanana.model.*
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyDonationsViewModel : ViewModel() {

    val donationsArray = MutableLiveData<ArrayList<DonacionData>>()

    fun obtainDonations(correo: Correo){
        println(Gson().toJson(JsonCorreo(correo)))

        val call = RetrofitInstance.servicioCovidApi.descargarDonaciones(JsonCorreo(correo))
        //println(call.request().url.toString())
        println(JsonCorreo(correo))
        call.enqueue(object: Callback<ArrayList<DonacionData>> {
            override fun onResponse(call: Call<ArrayList<DonacionData>>, response: Response<ArrayList<DonacionData>>) {
                if (response.isSuccessful) {
                    donationsArray.value = response.body()
                    println(response.body())
                    //answer.value = response.body().toString()
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
                    //answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    //println(answer.value)
                    println("No sesion :( 2")
                }
            }

            override fun onFailure(call: Call<ArrayList<DonacionData>>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
            }
        })
    }
}