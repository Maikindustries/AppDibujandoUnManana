package mx.itesm.appdibujandounmanana.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ForgotPasswordFragmentBinding

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

    private fun registerEvents(){
        binding.forgotPasswordSendCodeBtn.setOnClickListener{
            findNavController().navigate(R.id.loginFrag)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)
        // TODO: Use the ViewModel
        returnButton()
        registerEvents()

    }

    private fun returnButton(){
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.loginFrag)
        }
        callback.isEnabled
    }

}