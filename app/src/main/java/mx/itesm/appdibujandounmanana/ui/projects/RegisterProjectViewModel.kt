package mx.itesm.appdibujandounmanana.ui.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import mx.itesm.appdibujandounmanana.model.JsonProjectData
import mx.itesm.appdibujandounmanana.model.ProjectData
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterProjectViewModel : ViewModel() {

    val isSuccessful = MutableLiveData<Boolean>()

    fun registerProject(project: ProjectData){
        println(Gson().toJson(JsonProjectData(project)))
        val call = RetrofitInstance.servicioCovidApi.agregarProyecto(JsonProjectData(project))
        //println(call.request().url.toString())
        println("omg")
        println(JsonProjectData(project))
        call.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    isSuccessful.value = true

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
                    isSuccessful.value = false
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
                isSuccessful.value = false
            }
        })
    }
}