package mx.itesm.appdibujandounmanana.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.adminNavView

        val navController = findNavController(R.id.nav_host_fragment_activity_admin)

        navView.setupWithNavController(navController)

    }
}