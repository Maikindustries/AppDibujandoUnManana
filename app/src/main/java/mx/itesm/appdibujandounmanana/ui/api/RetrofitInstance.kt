package mx.itesm.appdibujandounmanana.ui.api

import mx.rmr.covid19.api.ServicioUserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("http://192.168.145.243:8080")
            .addConverterFactory(ScalarsConverterFactory.create())      // String. Int, etc
            .addConverterFactory(GsonConverterFactory.create())         // Json
            .build()
    }

    val servicioCovidApi: ServicioUserApi by lazy {
        retrofit.create(ServicioUserApi::class.java)
    }
}