package mx.itesm.appdibujandounmanana.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("http://192.168.1.85:8080")
            .addConverterFactory(ScalarsConverterFactory.create())      // String. Int, etc
            .addConverterFactory(GsonConverterFactory.create())         // Json
            .build()
    }

    val servicioCovidApi: ServicioUserApi by lazy {
        retrofit.create(ServicioUserApi::class.java)
    }
}