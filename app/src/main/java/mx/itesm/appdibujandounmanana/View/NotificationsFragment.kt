package mx.itesm.appdibujandounmanana.View

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.*
import mx.itesm.appdibujandounmanana.databinding.FragmentNotificationsBinding
import mx.itesm.appdibujandounmanana.model.NameData
import mx.itesm.appdibujandounmanana.ViewModel.NotificationsViewModel

//user
class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        isOrganization()
        getEmail()
        registerObservers()
        registerEvents()
        return root
    }

    private fun getEmail(){
        val preferencias = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
        val savedEmailPref = preferencias?.getString(KEY_EMAIL, "")
        if(savedEmailPref != null){
            binding.profileEmailText.text = savedEmailPref
        }
    }

    private fun getUserName(){
        //Obtener prefs de email registrado
        val preferencias = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
        val savedEmailPref = preferencias?.getString(KEY_EMAIL, "")
        if(savedEmailPref != null){
            notificationsViewModel.obtainUserNameData(NameData(savedEmailPref))
        }
    }

    private fun getOrganizationName(){
        //Obtener prefs de email registrado
        val preferencias = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
        val savedEmailPref = preferencias?.getString(KEY_EMAIL, "")
        if(savedEmailPref != null){
            notificationsViewModel.obtainOrganizationNameData(NameData(savedEmailPref))
        }
    }

    private fun registerObservers(){
        notificationsViewModel.userName.observe(viewLifecycleOwner) {
            binding.userNameText.text = it
            it
        }
        notificationsViewModel.organizationName.observe(viewLifecycleOwner){
            binding.organizationNameText.text = it
            it
        }
    }
    private fun isOrganization(){
        val preferencias = activity?.getSharedPreferences(
            PREFERENCES_ONBOARDING,
            AppCompatActivity.MODE_PRIVATE
        )
        val savedPref = preferencias?.getInt(KEY_ONBOARDING_INICIATED, -1)
        if (savedPref != null){
            if (savedPref == 2){
                getUserName()
                binding.userNameText.visibility = View.VISIBLE
                binding.profileOrganizationText.visibility = View.GONE
            }else{
                getOrganizationName()
                binding.userNameText.visibility = View.GONE
                binding.profileOrganizationText.visibility = View.VISIBLE
            }
        }
    }

    fun registerEvents(){
        //My donations button
        binding.profileMyDonationsBtn.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_myDonationsFragment)
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}