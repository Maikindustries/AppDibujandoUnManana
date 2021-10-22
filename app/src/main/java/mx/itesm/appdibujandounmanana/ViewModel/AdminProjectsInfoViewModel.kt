package mx.itesm.appdibujandounmanana.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.appdibujandounmanana.model.JsonProjectData
import mx.itesm.appdibujandounmanana.model.ProjectData
import mx.itesm.appdibujandounmanana.API.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminProjectsInfoViewModel : ViewModel() {

    val isSuccessfulApprove = MutableLiveData<Boolean>()
    val isSuccessfulRejection = MutableLiveData<Boolean>()

    fun approveProject(project: ProjectData){
        println(Gson().toJson(JsonProjectData(project)))
        val call = RetrofitInstance.servicioCovidApi.aceptarProyecto(JsonProjectData(project))
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
                    println("Error [${response.code()}] ${response.errorBody()}")

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

    fun rejectProject(project: ProjectData){
        println(Gson().toJson(JsonProjectData(project)))
        val call = RetrofitInstance.servicioCovidApi.rechazarProyecto(JsonProjectData(project))
        //println(call.request().url.toString())
        println(JsonProjectData(project))
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