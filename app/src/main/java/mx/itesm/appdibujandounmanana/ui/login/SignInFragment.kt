package mx.itesm.appdibujandounmanana.ui.login

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.SignInFragmentBinding
import kotlin.system.exitProcess

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel
    private lateinit var binding: SignInFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInFragmentBinding.inflate(layoutInflater)

        binding.sessionSignInButton.setOnClickListener {
            findNavController().navigate(R.id.loginFrag)
        }



        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            exitProcess(0)
            //println("no regreses atras pofavo")//si te vas atras no pasa nada
        }
        callback.isEnabled

        return binding.root
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        // TODO: Use the ViewModel

    }

}