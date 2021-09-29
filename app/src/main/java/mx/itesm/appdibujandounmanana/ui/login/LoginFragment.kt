package mx.itesm.appdibujandounmanana.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.KEY_ONBOARDING_INICIATED
import mx.itesm.appdibujandounmanana.MainActivity
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        binding.signInButton.setOnClickListener{
            val correo = binding.signInEmailEditText.text.toString()
            val contrasena = binding.signinPasswordEditText.text.toString()


            //petición a base de datos de login
            //petición de comprobar si es usuario u organización
            //if(es usuario){abre menu usuario} else {abre menu organizacion}

            /*if(correo.isNotEmpty() && contrasena.isNotEmpty()){
                //código de petición post
                if (correo == "mike" && contrasena == "1"){*/
                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)//abrir activity de aplicación principal
                    activity?.finish() //cerrar activity de login
                /*}else{
                    notifyWrongPassword()
                }
            }*/

            //Preferences
            val preferences = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
            preferences?.edit {
                putInt(KEY_ONBOARDING_INICIATED, 2)
                commit()
            }
        }

        binding.goRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFrag_to_registerFrag)
        }



        return binding.root
    }

    private fun returnButton(){
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.signInFrag)
        }
        callback.isEnabled
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
        returnButton()
    }

    private fun notifyWrongPassword() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Incorrect password")
            .setMessage("Try again. If you forgot your password select the button \"Forgot your password?\"")
            .setPositiveButton("Close") { _, _ ->}
        builder.show()
    }
}