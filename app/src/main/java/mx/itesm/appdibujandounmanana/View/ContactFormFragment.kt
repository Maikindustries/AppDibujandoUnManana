package mx.itesm.appdibujandounmanana.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ContactFormFragmentBinding
import mx.itesm.appdibujandounmanana.ViewModel.ContactFormViewModel

class ContactFormFragment : Fragment() {

    private lateinit var binding: ContactFormFragmentBinding

    private lateinit var viewModel: ContactFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ContactFormFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactFormViewModel::class.java)
        // TODO: Use the ViewModel
        registerEvents()
    }

    private fun registerEvents() {
        //register buttons
        binding.registerProjectRegisterBtn.setOnClickListener {
            if (binding.contactFirstNameEditText.text.isNotEmpty() && binding.contactLastNameEditText.text.isNotEmpty() &&
                binding.contactEmailTextEdit.text.isNotEmpty() && binding.contactPhoneEditText.text.isNotEmpty() &&
                binding.registerProjectDescriptionEditText.text.isNotEmpty()){

                Toast.makeText(activity, getString(R.string.message_sended), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.contactFragment)
            }else{
                notifyFillAllBlanks()
            }
        }
    }

    private fun notifyFillAllBlanks() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.missing_blanks))
            .setMessage(getString(R.string.fill_blanks))
            .setPositiveButton(getString(R.string.close)) { _, _ ->}
        builder.show()
    }

}