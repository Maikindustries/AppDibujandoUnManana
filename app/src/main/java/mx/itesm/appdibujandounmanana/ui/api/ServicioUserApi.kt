package mx.rmr.covid19.api;

import mx.itesm.appdibujandounmanana.model.Donacion
import mx.itesm.appdibujandounmanana.model.JsonUserData
import retrofit2.http.*


// https://disease.sh/v3/covid-19/hi    storical/mexico?lastdays=5
interface ServicioUserApi {
    //Usuario
    @POST("user/agregarUsuario")
    fun agregarUsuario(@Body userData: JsonUserData) : retrofit2.Call<String>
    @POST("user/IniciarSesion") //User Pass
    fun iniciarSesion(@Body userData: JsonUserData) : retrofit2.Call<String>
    @POST("user/validarCorreo")
    fun validarCorreo(@Body userData: JsonUserData) : retrofit2.Call<String>
    @POST("user/updatePassword")
    fun updatePassword(@Body userData: JsonUserData) : retrofit2.Call<String>

    //Organizacion
    @POST("user/agregarOrganizacion")
    fun agregarOrganizacion(@Body userData: JsonUserData) : retrofit2.Call<String>
    @POST("user/iniciarSesionOrganizacion")
    fun iniciarSesionOrganizacion(@Body userData: JsonUserData) : retrofit2.Call<String>

    //Donaciones
    @POST("user/registros")
    fun descargarDonaciones(/**/): retrofit2.Call<Donacion>

    //Admin
    @POST("user/proyectos")
    fun proyectos(/**/): retrofit2.Call<Donacion>

}