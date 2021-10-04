package mx.rmr.covid19.api;

import android.telecom.Call
import mx.itesm.appdibujandounmanana.model.Donacion
import mx.rmr.enviadatos.JsonUserData
import retrofit2.http.*


// https://disease.sh/v3/covid-19/historical/mexico?lastdays=5
interface ServicioUserApi {
    @POST("user/agregarUsuario")
    fun agregarUsuario(@Body userData: JsonUserData) : retrofit2.Call<String>

    @POST("user/iniciarSesion")
    fun iniciarSesion(/*@Body userData: JsonUsuario*/) : retrofit2.Call<String>

    @POST("user/registros")
    fun descargarDonaciones(/**/): retrofit2.Call<Donacion>
}