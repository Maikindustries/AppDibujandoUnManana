package mx.itesm.appdibujandounmanana.ui.projects

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
import mx.itesm.appdibujandounmanana.KEY_ONBOARDING_INICIATED
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.RegisterProjectFragmentBinding
import mx.itesm.appdibujandounmanana.model.ProjectData

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
        registerObservers()
    }

    private fun registerObservers(){
        viewModel.isSuccessful.observe(viewLifecycleOwner) {
            if (it){
                Toast.makeText(activity, "Succesful register", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity, "Unsuccesful register", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerEvents(){
        binding.registerProjectRegisterBtn.setOnClickListener {

            if(binding.registerProjectProjectNameEditText.text.toString().isNotEmpty() &&
                binding.registerProjectDescriptionEditText.text.toString().isNotEmpty()){

                //Obtener prefs de email registrado
                val preferencias = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
                val savedEmailPref = preferencias?.getString(KEY_EMAIL, "")
                if (savedEmailPref != null){
                    viewModel.registerProject(ProjectData(binding.registerProjectProjectNameEditText.text.toString(),
                        binding.registerProjectDescriptionEditText.text.toString(), savedEmailPref))//no se crea una relación en la base de datos
                }



                findNavController().navigateUp()
            }
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