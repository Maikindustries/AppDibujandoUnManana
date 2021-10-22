package mx.itesm.appdibujandounmanana.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.ProjectData
import mx.itesm.appdibujandounmanana.API.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminProjectsViewModel : ViewModel() {

    val projectsArray = MutableLiveData<ArrayList<ProjectData>>()

    fun obtainProjectsDataForAproval(){
        val call = RetrofitInstance.servicioCovidApi.proyectosPorAprobar()
        call.enqueue(object: Callback<ArrayList<ProjectData>> {
            override fun onResponse(call: Call<ArrayList<ProjectData>>, response: Response<ArrayList<ProjectData>>) {
                println(response.body())
                if (response.isSuccessful) {
                    response.body()?.let {
                        projectsArray.value = response.body()
                        println(it)
                    }

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
            override fun onFailure(call: Call<ArrayList<ProjectData>>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
            }
        })
    }
}