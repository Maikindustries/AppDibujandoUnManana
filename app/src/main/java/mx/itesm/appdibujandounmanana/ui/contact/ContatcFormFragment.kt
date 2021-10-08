package mx.itesm.appdibujandounmanana.ui.contact

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ContatcFormFragmentBinding

class ContatcFormFragment : Fragment() {

    private lateinit var binding: ContatcFormFragmentBinding

    private lateinit var viewModel: ContatcFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContatcFormFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContatcFormViewModel::class.java)
        // TODO: Use the ViewModel
        registerEvents()
    }

    private fun registerEvents() {
        //register buttons
        binding.registerProjectRegisterBtn.setOnClickListener {
            if (binding.contactFirstNameEditText.text.isNotEmpty() && binding.contactLastNameEditText.text.isNotEmpty() &&
                binding.contactEmailTextEdit.text.isNotEmpty() && binding.contactPhoneEditText.text.isNotEmpty() &&
                binding.registerProjectDescriptionEditText.text.isNotEmpty()){

                //send email
                //if send email is true
                //successfl message
                //else
                //unsuccessful message

                Toast.makeText(activity, "Message sended", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.contactFragment)
            }else{
                notifyFillAllBlanks()
            }
        }
    }

    private fun notifyFillAllBlanks() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Missing blanks")
            .setMessage("Please fill all the blanks")
            .setPositiveButton("Close") { _, _ ->}
        builder.show()
    }

}