package mx.itesm.appdibujandounmanana.API;

import mx.itesm.appdibujandounmanana.model.*
import retrofit2.http.*

interface ServicioUserApi {
    //Usuario
    @POST("user/agregarUsuario")
    fun agregarUsuario(@Body userData: JsonUserData) : retrofit2.Call<String>
    @POST("user/iniciarSesion") //User Pass
    fun iniciarSesion(@Body UserInicioSesion: JsonInicioSesion) : retrofit2.Call<String>
    @POST("user/validarCorreo")
    fun validarCorreo(@Body Correo: JsonCorreo) : retrofit2.Call<String>
    @POST("user/updatePassword")
    fun updatePassword(@Body UserInicioSesion: JsonInicioSesion) : retrofit2.Call<String>
    @POST("user/nombreUsuario")
    fun getUserName(@Body nameData: NameData): retrofit2.Call<String>

    //Organizacion
    @POST("user/agregarOrganizacion")
    fun agregarOrganizacion(@Body organizationData: JsonOrganizationData) : retrofit2.Call<String>
    @POST("user/iniciarSesionOrganizacion")
    fun iniciarSesionOrganizacion(@Body organizationData: JsonOrganizacionInicioSesion) : retrofit2.Call<String>
    @POST("user/organizacionesPorAceptar")
    fun organizacionesPorAceptar() : retrofit2.Call<ArrayList<OrganizationData>>
    @POST("user/organizacionesAceptadas")
    fun organizacionesAceptadas() : retrofit2.Call<String>
    @POST("user/aceptarOrganizacion")
    fun aceptarOrganizacion(@Body organizationData: JsonOrganizationData) : retrofit2.Call<String>
    @POST("user/rechazarOrganizacion")
    fun rechazarOrganizacion(@Body organizationData: JsonOrganizationData) : retrofit2.Call<String>
    @POST("user/nombreOrganizacion")
    fun getOrganizationName(@Body nameData: NameData): retrofit2.Call<String>

    //Proyecto
    @POST("user/agregarProyecto")
    fun agregarProyecto(@Body projectData: JsonProjectData) : retrofit2.Call<String>
    @POST("user/listaproyectosAceptados")
    fun listaProyectosAceptados() : retrofit2.Call<List<ProjectData>>
    @POST("user/listaProyectosRechazados")
    fun listaProyectosRechazados() : retrofit2.Call<String>
    @POST("user/aceptarProyecto")
    fun aceptarProyecto(@Body projectData: JsonProjectData) : retrofit2.Call<String>
    @POST("user/rechazarProyecto")
    fun rechazarProyecto(@Body projectData: JsonProjectData) : retrofit2.Call<String>


    //Donaciones
    @POST("user/registros")
    fun descargarDonaciones(@Body Correo: JsonCorreo): retrofit2.Call<ArrayList<DonacionData>>
    @POST("user/hacerDonacion")
    fun registrarDonacion(@Body donacionData: DonacionData): retrofit2.Call<String>

    //Admin
    @POST("user/listaProyectosPorAceptar")
    fun proyectosPorAprobar(): retrofit2.Call<ArrayList<ProjectData>>

    //Proyectos
    @POST("user/listaProyectosAceptados")
    fun obtenerProyectosAceptados() : retrofit2.Call<ArrayList<JsonProjectData>>

    @POST("user/forgot")
    fun olvidoContrasena(@Body email: Correo) : retrofit2.Call<String>
    @POST("user/recover")
    fun recuperarContrasena(@Body recoveryData: RecoveryData) : retrofit2.Call<String>
}