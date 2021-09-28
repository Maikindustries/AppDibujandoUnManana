package mx.rmr.covid19.api;

import android.telecom.Call
import mx.rmr.enviadatos.JsonUserData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


// https://disease.sh/v3/covid-19/historical/mexico?lastdays=5
interface ServicioUserApi {
    @POST("user/agregarUsuarioData")
    fun enviarAlumno(@Body userData: JsonUserData) : retrofit2.Call<String>
}