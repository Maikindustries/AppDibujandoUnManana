package mx.itesm.appdibujandounmanana.Admin.AdminOrganizations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.itesm.appdibujandounmanana.model.OrganizationData
import mx.itesm.appdibujandounmanana.ui.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminVerifyOrganizationViewModel : ViewModel() {

    val organizationsArray = MutableLiveData<ArrayList<OrganizationData>>()

    fun obtainOrganizationsDataForAproval(){
        val call = RetrofitInstance.servicioCovidApi.organizacionesPorAceptar()
        call.enqueue(object: Callback<ArrayList<OrganizationData>> {
            override fun onResponse(call: Call<ArrayList<OrganizationData>>, response: Response<ArrayList<OrganizationData>>) {
                println(response.body())
                println("organization approval")
                if (response.isSuccessful) {
                    response.body()?.let {
                        organizationsArray.value = response.body()
                        println(it)
                    }

                } else {
                    println("No sesion :( 2")
                }
            }
            override fun onFailure(call: Call<ArrayList<OrganizationData>>, t: Throwable) {
                //answer.value = "Error, ${t.localizedMessage}"
                //println(answer.value)
                println("No sesion :( 3")
            }
        })
    }
}