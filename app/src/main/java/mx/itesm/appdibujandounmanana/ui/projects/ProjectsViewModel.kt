package mx.itesm.appdibujandounmanana.ui.projects

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.JsonOrganizationData
import mx.itesm.appdibujandounmanana.model.JsonProjectData
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectsViewModel : ViewModel() {

    val projectsArray = MutableLiveData<ArrayList<ProjectsCardModel>>()

    /*fun obtainApprovedProjectsData(){
        //println(Gson().toJson(JsonInicioSesion(user)))

        val call = RetrofitInstance.servicioCovidApi.obtenerProyectosAceptados()
        //println(call.request().url.toString())
        call.enqueue(object: Callback<ArrayList<JsonProjectData>> {
            override fun onResponse(call: Call<ArrayList<JsonProjectData>>, response: Response<ArrayList<JsonProjectData>>) {
                if (response.isSuccessful) {
                    projectsArray.value?.add(response.body())
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
            override fun onFailure(call: Call<ArrayList<JsonProjectData>>, t: Throwable) {
                answer.value = "Error, ${t.localizedMessage}"
                println(answer.value)
                println("No sesion :( 3")
            }
        })
    }*/
}