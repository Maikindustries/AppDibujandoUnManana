package mx.itesm.appdibujandounmanana.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.RegisterFragmentBinding
import mx.itesm.appdibujandounmanana.model.OrganizationData
import mx.itesm.appdibujandounmanana.model.UserData
import retrofit2.Retrofit
import java.lang.Double.parseDouble
import java.lang.Integer.parseInt
import java.lang.Long.parseLong
import java.math.BigInteger
import java.security.MessageDigest


class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: RegisterFragmentBinding

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

        //selectDate()
        configureEvents()
        registerObservers()
    }


    fun registerObservers(){
        viewModel.userAnswer.observe(viewLifecycleOwner, {
            if (it == "YES"){
                notifyVerifyEmail()
            }else {
                notifyExistentEmail()
            }
            it
        })
        viewModel.organizationAnswer.observe(viewLifecycleOwner, {
            if (it == "NO"){
                notifyExistentEmail()
            }else {
                notifyVerifyEmail()
            }
            it
        })
    }

    private fun configureEvents() {
        binding.registerOrganizationCheckBox.setOnClickListener{
            if(binding.registerOrganizationCheckBox.isChecked){
                binding.registerOrganizationFormLayout.visibility = View.VISIBLE
                binding.registerUserFormLayout.visibility = View.GONE
            }else{
                binding.registerOrganizationFormLayout.visibility = View.GONE
                binding.registerUserFormLayout.visibility = View.VISIBLE
            }
        }

        //Is organization ya quedó
        binding.registerRegisterOrganizationBtn.setOnClickListener {
            if (binding.registerOrganizationNameEditText.text.isNotEmpty() && binding.registerOrganizationTagEditText.text.isNotEmpty() &&
                binding.registerOrganizationEmailEditText.text.isNotEmpty() && binding.registerOrganizationDescriptionEditText.text.isNotEmpty() &&
                binding.registerOrganizationPasswordEditText.text.isNotEmpty() && binding.registerOrganizationRepeatPasswordEditText.text.isNotEmpty() &&
                binding.registerOrganizationPhoneEditText.text.isNotEmpty()){

                if(binding.registerOrganizationPasswordEditText.text.toString() == binding.registerOrganizationRepeatPasswordEditText.text.toString()){

                    //verify valid number input
                    val string = binding.registerOrganizationPhoneEditText.text.toString()
                    var numeric = true
                    try { val num = parseLong(string) }
                    catch (e: NumberFormatException) { numeric = false }
                    if (numeric) {
                        println("$string is a number")
                    }else {
                        notifyInvalidNumber()
                    }


                    val nuevoRegistro = OrganizationData(binding.registerOrganizationNameEditText.text.toString(),
                        binding.registerOrganizationTagEditText.text.toString(),
                        binding.registerOrganizationDescriptionEditText.text.toString(),
                        getSHA512(binding.registerOrganizationPasswordEditText.text.toString()),
                        binding.registerOrganizationPhoneEditText.text.toString(),
                        binding.registerOrganizationEmailEditText.text.toString())

                    viewModel.organizationRegister(nuevoRegistro)

                    /*if (viewModel.organizationAnswer.value == "NO"){
                        notifyExistentEmail()
                    }else
                        notifyVerifyEmail()*/
                }else {
                    notifyPasswordsDontMatch()
                }
            }else{
                notifyFillAllBlanks()
            }
        }


        //Is user ya quedó
        binding.registerRegisterUserBtn.setOnClickListener {
            //Verify if all the blanks are filled
            if (binding.registerEmailEditText.text.isNotEmpty() && binding.registerNamesEditText.text.isNotEmpty() &&
                binding.registerLastNameEditText.text.isNotEmpty() && binding.registerPasswordEditText.text.isNotEmpty() &&
                binding.registerRepeatPasswordEditText.text.isNotEmpty() && binding.registerDayEditText.text.isNotEmpty() &&
                binding.registerMonthEditText.text.isNotEmpty() && binding.registerYearEditText.text.isNotEmpty() &&
                binding.registerOrganizationPhoneEditText.text.toString().isNotEmpty()){

                //Verify that password and repeat password are the same
                if (binding.registerPasswordEditText.text.toString() == binding.registerRepeatPasswordEditText.text.toString()){

                    val salt: String = java.util.UUID.randomUUID().toString() // 8-4-4-4-20 (32 hexadecimales)
                    val nuevoRegistro = UserData(
                        binding.registerEmailEditText.text.toString(),
                        (binding.registerNamesEditText.text.toString()+binding.registerLastNameEditText.text.toString()),
                        getSHA512(binding.registerPasswordEditText.text.toString()) + salt,
                        salt, //salt
                        binding.registerOrganizationPhoneEditText.text.toString(),
                        binding.registerYearEditText.text.toString()+"-"+binding.registerMonthEditText.text.toString()+"-"+binding.registerDayEditText.text.toString(),
                        false
                    )
                    viewModel.userRegister(nuevoRegistro)
                    /*if (viewModel.userAnswer.value.toString() == "YES"){
                        notifyVerifyEmail()
                    }else {
                        notifyExistentEmail()
                    }*/
                }else {
                    notifyPasswordsDontMatch()
                }
            }else{
                notifyFillAllBlanks()
            }
        }
    }

    private fun notifyInvalidNumber() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Invalid number")
            .setMessage("Please write a valid number")
            .setPositiveButton("OK") { _, _ ->
                findNavController().navigateUp()
            }
        builder.show()
    }

    private fun notifyVerifyEmail() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Account created succesfully")
            .setMessage("We´ll send you an email. Please click the link to confirm your email.")
            .setPositiveButton("OK") { _, _ ->
                findNavController().navigateUp()
            }
        builder.show()
    }

    private fun notifyExistentEmail() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("The email is asociated with a account")
            .setMessage("Try another email or sign in.")
            .setNegativeButton("Log in to your account") { _, _ ->
                findNavController().navigateUp()
            }
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

    //Crypto
    private fun getSHA512(input:String):String{
        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        val messageDigest = md.digest(input.toByteArray())

        val no = BigInteger(1, messageDigest)

        var hashtext: String = no.toString(16)

        while (hashtext.length < 128) {
            hashtext = "0$hashtext"
        }

        return hashtext
    }



}