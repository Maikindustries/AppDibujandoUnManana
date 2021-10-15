package mx.itesm.appdibujandounmanana.ui.projects

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.RegisterProjectFragmentBinding

class RegisterProjectFragment : Fragment() {

    private lateinit var binding: RegisterProjectFragmentBinding

    private lateinit var viewModel: RegisterProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterProjectFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterProjectViewModel::class.java)
        // TODO: Use the ViewModel
        registerEvents()
        returnButton()
    }

    private fun registerEvents(){
        binding.registerProjectRegisterBtn.setOnClickListener {
            Toast.makeText(activity, "Succesful register", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    private fun returnButton(){
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            //exitProcess(0)//este mata la app pero sigue en ram y se ve fea en app preview
            findNavController().navigateUp()
        }
        callback.isEnabled
    }

}