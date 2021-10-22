package mx.itesm.appdibujandounmanana.View

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import mx.itesm.appdibujandounmanana.ViewModel.AdminProfileViewModel
import mx.itesm.appdibujandounmanana.KEY_ONBOARDING_INICIATED
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.AdminProfileFragmentBinding

class AdminProfileFragment : Fragment() {

    private lateinit var binding: AdminProfileFragmentBinding

    private lateinit var viewModel: AdminProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AdminProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminProfileViewModel::class.java)
        // TODO: Use the ViewModel
        registerEvents()
    }

    fun registerEvents(){
        binding.logOutBtn.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle(R.string.log_out_message_1)
                .setMessage(R.string.log_out_message_2)
                .setPositiveButton(R.string.yes) { _, _ ->
                    //Change preferences to 1
                    val preferences = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
                    preferences?.edit {
                        putInt(KEY_ONBOARDING_INICIATED, 1)
                        commit()
                    }

                    //Launch login activity
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
                .setNegativeButton("No"){_, _ ->}
            builder.show()
        }
    }

}