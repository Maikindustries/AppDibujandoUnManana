package mx.itesm.appdibujandounmanana.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.KEY_EMAIL
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ForgotPasswordFragmentBinding
import mx.itesm.appdibujandounmanana.model.CodigoData
import mx.itesm.appdibujandounmanana.model.Correo

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: ForgotPasswordFragmentBinding

    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ForgotPasswordFragmentBinding.inflate(layoutInflater)
        return binding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)
        // TODO: Use the ViewModel
        returnButton()
        //registerEvents()
        //registerObservers()
    }

    /*private fun registerObservers(){
        viewModel.answerForgotPassword.observe(viewLifecycleOwner) {
            if(it == "YES"){
                binding.forgotPasswordConfirmCodeLayout.visibility = View.VISIBLE
            }
            it
        }
        viewModel.answerRecoverPassword.observe(viewLifecycleOwner) {
            if(it == "YES"){
                //binding.forgotPasswordChangePasswordLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun registerEvents(){
        binding.forgotPasswordSendCodeBtn.setOnClickListener{
            forgotPassword()
        }
        binding.forgotPasswordRegisterNewPasswordBtn.setOnClickListener {
            if(binding.forgotPasswordCodeEditText.text.toString().isNotEmpty()){
                viewModel.recoverPassword(CodigoData(binding.forgotPasswordCodeEditText.text.toString()))
            }

        }
    }


    private fun forgotPassword(){
        val preferencias = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
        val savedEmailPref = preferencias?.getString(KEY_EMAIL, "")
        if (savedEmailPref != null){
            viewModel.forgotPassword(Correo(savedEmailPref))
        }
    }*/

    private fun returnButton(){
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.loginFrag)
        }
        callback.isEnabled
    }

}