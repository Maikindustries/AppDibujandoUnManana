package mx.itesm.appdibujandounmanana.Admin.AdminOrganizations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.appdibujandounmanana.model.JsonOrganizationData
import mx.itesm.appdibujandounmanana.model.OrganizationData
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminOrganizationInfoViewModel : ViewModel() {

    val isSuccessfulApprove = MutableLiveData<Boolean>()
    val isSuccessfulRejection = MutableLiveData<Boolean>()

    fun approveOrganization(organization: OrganizationData){
        println(Gson().toJson(JsonOrganizationData(organization)))
        val call = RetrofitInstance.servicioCovidApi.aceptarOrganizacion(JsonOrganizationData(organization))
        //println(call.request().url.toString())
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    isSuccessfulApprove.value = response.body().toString() == "[1]"
                    if(isSuccessfulApprove.value==true){
                        println("exitoso")
                    }else{
                        println("no exitoso")
                    }

                } else {
                    //answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    //println(answer.value)
                    println("No sesion :( 2")
                    isSuccessfulApprove.value = false
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
                isSuccessfulApprove.value = false
            }
        })
    }

    fun rejectOrganization(organization: OrganizationData){
        println(Gson().toJson(JsonOrganizationData(organization)))
        val call = RetrofitInstance.servicioCovidApi.rechazarOrganizacion(JsonOrganizationData(organization))
        //println(call.request().url.toString())
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    //println(response.body())
                    isSuccessfulRejection.value = response.body().toString().toInt() == 1
                    if(isSuccessfulRejection.value==true){
                        println("exitoso")
                    }else{
                        println("no exitoso")
                    }

                } else {
                    //answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    //println(answer.value)
                    println("No sesion :( 2")
                    isSuccessfulRejection.value = false
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
                isSuccessfulRejection.value = false
            }
        })
    }
}