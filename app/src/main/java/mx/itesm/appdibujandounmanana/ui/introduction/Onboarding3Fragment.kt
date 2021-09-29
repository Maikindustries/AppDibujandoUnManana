package mx.itesm.appdibujandounmanana.ui.introduction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import mx.itesm.appdibujandounmanana.*
import mx.itesm.appdibujandounmanana.databinding.FragmentOnboarding3Binding
import mx.itesm.appdibujandounmanana.ui.login.LoginActivity

class Onboarding3Fragment : Fragment() {



    private var _binding: FragmentOnboarding3Binding? = null
    //private val PREFERENCE_KEY = "playerprefs"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboarding3Binding.inflate(inflater, container, false)
        val root: View = binding.root


        registerEvents()
        return binding.root
    }


    private fun registerEvents(){
        binding.introductionContinueBtn.setOnClickListener {
            //Change the value of prefs to maintain open session
            val preferences = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
            preferences?.edit {
                putInt(KEY_ONBOARDING_INICIATED, 1)
                commit()
            }

            //Change to login activity
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()//terminar esta actividad
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}