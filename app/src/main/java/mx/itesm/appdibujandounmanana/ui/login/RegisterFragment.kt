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

    var formatDate = SimpleDateFormat("dd/mm/y", Locale.US)

    // Pagina de prueba
    var retrofit = Retrofit.Builder()
        .baseUrl("https://disease.sh/")
        .build()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(layoutInflater)


        binding.registerRegisterButton.setOnClickListener {

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
                //Toast de correo con cuenta existente, use otro correo o olvidó su contraseña?.
            //}

            Toast.makeText(activity, "Succesful register", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.loginFrag)
        }

        /*val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.registerDateOfBirthButton.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                binding.registerDateOfBirthText.text = (" ${mDay}/${mMonth}/${mYear}")
            }, year, month, day)
            dpd.show()
        }*/




        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
        selectDate()
    }

    private fun selectDate() {

        binding.registerDateOfBirthButton.setOnClickListener(View.OnClickListener {
            val getDate = Calendar.getInstance()
            val datepicker = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Dialog_MinWidth,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    val selectDate = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, i)
                    selectDate.set(Calendar.MONTH, i2)
                    val date = formatDate.format(selectDate.time)
                    binding.registerDateOfBirthText.text = date

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