package mx.itesm.appdibujandounmanana.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.JsonNombreData
import mx.itesm.appdibujandounmanana.model.NombreData
import mx.itesm.appdibujandounmanana.model.ProjectData
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsViewModel : ViewModel() {

    var userName = MutableLiveData<String>()

    fun obtainUserNameData(nombreData: NombreData){
        //println(Gson().toJson(JsonInicioSesion(user)))
        println(nombreData)
        val call = RetrofitInstance.servicioCovidApi.obtenerNombreUsuario(nombreData)
        //println(call.request().url.toString())
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    userName.value = response.body()

                } else {
                    //answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    //println(answer.value)
                    println("No sesion :( 2")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
            }
        })
    }
}