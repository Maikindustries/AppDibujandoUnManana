package mx.itesm.appdibujandounmanana.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.KEY_EMAIL
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ForgotPasswordFragmentBinding
import mx.itesm.appdibujandounmanana.model.Correo
import mx.itesm.appdibujandounmanana.model.RecoveryData
import mx.itesm.appdibujandounmanana.ViewModel.ForgotPasswordViewModel

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

        returnButton()
        registerEvents()
        registerObservers()
    }

    private fun registerObservers(){
        viewModel.answerForgotPassword.observe(viewLifecycleOwner) {
            if(it == "YES"){
                binding.forgotPasswordConfirmCodeLayout.visibility = View.VISIBLE
            }
        }
        viewModel.answerRecoverPassword.observe(viewLifecycleOwner) {
            if(it == "YES"){
                Toast.makeText(activity, "Password changed successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    private fun registerEvents(){
        binding.forgotPasswordSendCodeBtn.setOnClickListener{
            val preferencias = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
            val savedEmailPref = preferencias?.getString(KEY_EMAIL, "")
            if (savedEmailPref != null){
                viewModel.forgotPassword(Correo(savedEmailPref))
            }
        }
        binding.forgotPasswordRegisterNewPasswordBtn.setOnClickListener {
            if(binding.forgotPasswordCodeEditText.text.toString().isNotEmpty() &&
                binding.forgotPasswordNewPasswordEditText.text.toString().isNotEmpty() &&
                binding.forgotPasswordRepeatNewPasswordEditText.text.toString().isNotEmpty()){
                val preferencias = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
                val savedEmailPref = preferencias?.getString(KEY_EMAIL, "")
                    if(binding.forgotPasswordNewPasswordEditText.text.toString() == binding.forgotPasswordRepeatNewPasswordEditText.text.toString()){
                        if (savedEmailPref != null){
                            viewModel.recoverPassword(RecoveryData(savedEmailPref,binding.forgotPasswordCodeEditText.text.toString()))
                        }

                    }
            }
        }
    }



    private fun returnButton(){
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.loginFrag)
        }
        callback.isEnabled
    }

}