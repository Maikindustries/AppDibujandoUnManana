package mx.itesm.appdibujandounmanana.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import mx.itesm.appdibujandounmanana.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        openLoginActivity()
    }

    private fun openLoginActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, IntroductionActivity::class.java)
            startActivity(intent)
            this.finish()
        }, 500)
    }
}