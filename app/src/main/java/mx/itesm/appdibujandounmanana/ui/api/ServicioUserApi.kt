package mx.rmr.covid19.api;

import mx.itesm.appdibujandounmanana.model.*
import retrofit2.http.*

interface ServicioUserApi {
    //Usuario
    @POST("user/agregarUsuario")
    fun agregarUsuario(@Body userData: JsonUserData) : retrofit2.Call<String>
    @POST("user/IniciarSesion") //User Pass
    fun iniciarSesion(@Body UserInicioSesion: JsonInicioSesion) : retrofit2.Call<String>
    @POST("user/validarCorreo")
    fun validarCorreo(@Body Correo: JsonCorreo) : retrofit2.Call<String>
    @POST("user/updatePassword")
    fun updatePassword(@Body UserInicioSesion: JsonInicioSesion) : retrofit2.Call<String>

    //Organizacion
    @POST("user/agregarOrganizacion")
    fun agregarOrganizacion(@Body userData: JsonUserData) : retrofit2.Call<String>
    @POST("user/iniciarSesionOrganizacion")
    fun iniciarSesionOrganizacion(@Body userData: JsonUserData) : retrofit2.Call<String>

    //Donaciones
    @POST("user/registros")
    fun descargarDonaciones(@Body Correo: JsonCorreo): retrofit2.Call<Donacion>

    //Admin
    @POST("user/proyectos")
    fun proyectos(): retrofit2.Call<Proyecto>

}