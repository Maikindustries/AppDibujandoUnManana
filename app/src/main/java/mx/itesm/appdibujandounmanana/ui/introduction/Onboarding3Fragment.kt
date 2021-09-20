package mx.itesm.appdibujandounmanana.ui.introduction

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.FragmentOnboarding3Binding
import mx.itesm.appdibujandounmanana.ui.login.LoginActivity

class Onboarding3Fragment : Fragment() {



    private var _binding: FragmentOnboarding3Binding? = null
    //private val PREFERENCE_KEY = "playerprefs"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    /*init{
        if(getIntroductionPreferences()){//si ya se ha inciado sesión o registrado ya no se muestra onboarding
            val intent = Intent(activity, Login::class.java)
            startActivity(intent)
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboarding3Binding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.introductionContinueBtn.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()//terminar esta actividad
        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}