package mx.itesm.appdibujandounmanana.ui.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.*
import mx.itesm.appdibujandounmanana.databinding.LoginFragmentBinding
import mx.itesm.appdibujandounmanana.model.JsonInicioSesion
import mx.itesm.appdibujandounmanana.model.OrganizacionInicioSesion
import mx.itesm.appdibujandounmanana.model.UserInicioSesion

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(layoutInflater)

        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
        returnButton()
        registerEvents()
        login()
        registerObservers()
    }

    private fun registerEvents() {
        //Register button
        binding.goRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFrag_to_registerFrag)
        }
        //Forgot password
        binding.signInForgotPasswordBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFrag_to_forgotPasswordFragment)
        }
    }

    private fun registerObservers() {
        viewModel.answer.observe(viewLifecycleOwner, {
            it
        })
        viewModel.organizationAnswer.observe(viewLifecycleOwner, {
            it
        })
    }

    private fun login() {
        //checkbox
        binding.signInIsOrganizationCheckBox.setOnClickListener {
            if (binding.signInIsOrganizationCheckBox.isChecked) {
                binding.signInUserButton.visibility = View.GONE
                binding.signInOrganizationButton.visibility = View.VISIBLE
            } else {
                binding.signInUserButton.visibility = View.VISIBLE
                binding.signInOrganizationButton.visibility = View.GONE
            }
        }

        binding.signInUserButton.setOnClickListener {
            //Preferences
            val preferences = activity?.getSharedPreferences(
                PREFERENCES_ONBOARDING,
                AppCompatActivity.MODE_PRIVATE
            )

            val email = binding.signInEmailEditText.text.toString()
            val password = binding.signInPasswordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                //petición a base de datos de login
                val newSesion = UserInicioSesion(email, password)
                viewModel.userLogIn(newSesion)

                Handler(Looper.getMainLooper()).postDelayed({
                    //poner en listener
                    //petición de comprobar si es usuario u organización
                    if (viewModel.answer.value.toString() == "SINORMAL") {

                        preferences?.edit {
                            putInt(KEY_ONBOARDING_INICIATED, 2)
                            commit()
                        }
                        preferences?.edit {
                            putString(KEY_EMAIL, email)
                            commit()
                        }
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    } else if (viewModel.answer.value.toString() == "SIADMIN") {
                        preferences?.edit {
                            putInt(KEY_ONBOARDING_INICIATED, 4)
                            commit()
                        }
                        preferences?.edit {
                            putString(KEY_EMAIL, email)
                            commit()
                        }
                        val intent = Intent(activity, AdminActivity::class.java)
                        startActivity(intent)
                        activity?.finish()

                    } else if (viewModel.answer.value.toString() == "NO") {
                        notifyWrongPassword()
                    } else {
                        notifyEmailNotRegistered()
                    }

                }, 300)
            }
        }

        //checar que funcione
        binding.signInOrganizationButton.setOnClickListener {
            //Preferences
            val preferences = activity?.getSharedPreferences(
                PREFERENCES_ONBOARDING,
                AppCompatActivity.MODE_PRIVATE
            )

            val email = binding.signInEmailEditText.text.toString()
            val password = binding.signInPasswordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                //petición a base de datos de login
                val newSesion = OrganizacionInicioSesion(email, password)
                viewModel.organizationLogIn(newSesion)

                Handler(Looper.getMainLooper()).postDelayed({
                    if (viewModel.organizationAnswer.value == "YES") {
                        preferences?.edit {
                            putInt(KEY_ONBOARDING_INICIATED, 3)
                            commit()
                        }
                        preferences?.edit {
                            putString(KEY_EMAIL, email)
                            commit()
                        }
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    } else if (viewModel.organizationAnswer.value == "NO") {
                        notifyWrongPassword()
                    } else {
                        notifyEmailNotRegistered()
                    }
                }, 500)


            }
        }
    }

    private fun notifyExistentEmail() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("The email is asociated with a account")
            .setMessage("Try another email or sign in.")
            .setNegativeButton("Log in to your account") { _, _ ->
                findNavController().navigateUp()
            }
            .setPositiveButton("Use another emal") { _, _ -> }
        builder.show()
    }

    private fun returnButton() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.signInFrag)
        }
        callback.isEnabled
    }

    fun notifyWrongPassword() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Incorrect password")
            .setMessage("Try again. If you forgot your password select the button \"Forgot your password?\"")
            .setPositiveButton("Close") { _, _ -> }
        builder.show()
    }

    private fun notifyEmailNotRegistered() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("The email is not associated with an account.")
            .setMessage("Go to register to use that email")
            .setPositiveButton("OK") { _, _ -> }
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

}