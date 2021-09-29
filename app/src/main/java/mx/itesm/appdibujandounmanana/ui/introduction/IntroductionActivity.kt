package mx.itesm.appdibujandounmanana.ui.introduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.itesm.appdibujandounmanana.KEY_ONBOARDING_INICIATED
import mx.itesm.appdibujandounmanana.MainActivity
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ActivityIntroductionBinding
import mx.itesm.appdibujandounmanana.ui.login.LoginActivity

class IntroductionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)


        cargarPreferencias()
    }

    // Recupera la preferencia guardada previamente
    private fun cargarPreferencias() {
        val preferencias = this.getSharedPreferences(
            PREFERENCES_ONBOARDING,
            AppCompatActivity.MODE_PRIVATE
        )//modo privado es que sólo se pueden acceder aquí
        val favorito = preferencias?.getInt(KEY_ONBOARDING_INICIATED, -1)
        when(favorito){
            (1) -> {
                //Close onboarding activity and got to main menu
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.finish()//terminar esta actividad
            }
            (2) -> {
                println("open main menu")
                //Close onboarding activity and got to main menu
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                this.finish()//terminar esta actividad
            }
            else -> {
                println("Open onboarding fragments")
            }
        }
        if (favorito == 1){
            //Close onboarding activity and got to main menu
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()//terminar esta actividad
        }
    }
}