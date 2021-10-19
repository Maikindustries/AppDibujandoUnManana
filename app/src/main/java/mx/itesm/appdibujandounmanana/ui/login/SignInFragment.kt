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


        returnButton()
        registerEvents()
        return binding.root
    }

    private fun registerEvents(){
        //log in view
        binding.sessionSignInButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFrag_to_loginFrag)
        }
        binding.sessionDonateButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFrag_to_signInDonateFragment)
        }
    }

    private fun returnButton(){
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            //exitProcess(0)//este mata la app pero sigue en ram y se ve fea en app preview
            activity?.finish()//este cierra la app pero no la mata y en app preview se ve bien
        }
        callback.isEnabled
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        // TODO: Use the ViewModel

    }

}