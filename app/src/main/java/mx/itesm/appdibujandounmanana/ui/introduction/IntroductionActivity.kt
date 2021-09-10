package mx.itesm.appdibujandounmanana.ui.introduction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ActivityIntroductionBinding

class IntroductionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

    }
}