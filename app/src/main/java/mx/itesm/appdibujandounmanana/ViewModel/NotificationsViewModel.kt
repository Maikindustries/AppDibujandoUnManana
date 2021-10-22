package mx.itesm.appdibujandounmanana.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.NameData
import mx.itesm.appdibujandounmanana.API.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsViewModel : ViewModel() {

    var userName = MutableLiveData<String>()
    var organizationName = MutableLiveData<String>()

    fun obtainUserNameData(nombreData: NameData){
        //println(Gson().toJson(JsonInicioSesion(user)))
        println(nombreData)
        val call = RetrofitInstance.servicioCovidApi.getUserName(nombreData)
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

    fun obtainOrganizationNameData(nombreData: NameData){
        //println(Gson().toJson(JsonInicioSesion(user)))
        println(nombreData)
        val call = RetrofitInstance.servicioCovidApi.getOrganizationName(nombreData)
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    println(response.body())
                    organizationName.value = response.body()

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