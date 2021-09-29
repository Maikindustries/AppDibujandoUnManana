package mx.itesm.appdibujandounmanana.ui.login

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.RegisterFragmentBinding
import mx.rmr.enviadatos.UserData
import java.util.*
import retrofit2.Retrofit
import java.text.SimpleDateFormat


class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: RegisterFragmentBinding

    var formatDate = SimpleDateFormat("dd/MM/YYYY", Locale.US)

    // Pagina de prueba
    var retrofit = Retrofit.Builder()
        .baseUrl("https://disease.sh/")
        .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(layoutInflater)




        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        selectDate()
        configureEvents()
    }


    fun configureEvents() {
        binding.registerRegisterButton.setOnClickListener {
            //Verify if all the blanks are filled
            if (binding.registerEmailEditText.text.isNotEmpty() && binding.namesEditText.text.isNotEmpty() &&
                binding.lastNameEditText.text.isNotEmpty() && binding.registerPasswordEditText.text.isNotEmpty() &&
                binding.registerRepeatPasswordEditText.text.isNotEmpty() && binding.registerDateOfBirthText.text.isNotEmpty()){

                //Verify that password and repeat password are the same
                if (binding.registerPasswordEditText.text.toString() == binding.registerRepeatPasswordEditText.text.toString()){
                    //codigo para registrar en base de datos
                    val nuevoRegistro = UserData(
                        binding.registerEmailEditText.text.toString(),
                        (binding.namesEditText.text.toString()+binding.lastNameEditText.text.toString()),
                        binding.registerPasswordEditText.text.toString(),
                        "1234", //salt
                        binding.registerDateOfBirthText.text.toString()
                    )
                    viewModel.registrarUsuario(nuevoRegistro)
                    //if(correo no existe en base de datos){
                    //hacer peticion de registro
                    //}else{
                    //  notifyExistentEmail()
                    //}

                    Toast.makeText(activity, "Succesful register", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.loginFrag)
                }else {
                    notifyPasswordsDontMatch()
                }
            }else{
                notifyFillAllBlanks()
            }
        }
    }


    private fun notifyExistentEmail() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("The email is asociated with a account")
            .setMessage("Try another email or sign in.")
            .setNegativeButton("Log in to your account") { _, _ ->}
            .setPositiveButton("Use another emal") { _, _ ->}
        builder.show()
    }


    private fun notifyPasswordsDontMatch() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("The passwords don't match")
            .setMessage("Please try again.")
            .setPositiveButton("Close") { _, _ ->}
        builder.show()
    }

    private fun notifyFillAllBlanks() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Missing blanks")
            .setMessage("Please fill all the blanks")
            .setPositiveButton("Close") { _, _ ->}
        builder.show()
    }

    private fun selectDate() {
        binding.registerDateOfBirthButton.setOnClickListener(View.OnClickListener {
            val getDate = Calendar.getInstance()
            val datepicker = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, i)
                    selectDate.set(Calendar.MONTH, i2)
                    val date = formatDate.format(selectDate.time)
                    binding.registerDateOfBirthText.text = date.toString()
                    selectDate.set(Calendar.DAY_OF_MONTH, i3)
                },
                getDate.get(Calendar.YEAR),
                getDate.get(Calendar.MONTH),
                getDate.get(Calendar.DAY_OF_MONTH)
            )
            datepicker.show()
        })
    }
}