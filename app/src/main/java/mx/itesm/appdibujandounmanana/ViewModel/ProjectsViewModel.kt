package mx.itesm.appdibujandounmanana.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.ProjectData
import mx.itesm.appdibujandounmanana.API.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProjectsViewModel : ViewModel() {

    val projectsArray = MutableLiveData<List<ProjectData>>()

    fun obtainApprovedProjectsData(){
        //println(Gson().toJson(JsonInicioSesion(user)))

        val call = RetrofitInstance.servicioCovidApi.listaProyectosAceptados()
        //println(call.request().url.toString())
        call.enqueue(object: Callback<List<ProjectData>> {
            override fun onResponse(call: Call<List<ProjectData>>, response: Response<List<ProjectData>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        projectsArray.value = response.body()
                        println(it)
                    }

                } else {
                    //answer.value = "Error [${response.code()}] ${response.errorBody()}"
                    //println(answer.value)
                    println("No sesion :( 2")
                }
            }
            override fun onFailure(call: Call<List<ProjectData>>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
            }
        })
    }
}