package mx.itesm.appdibujandounmanana.ui.notifications

import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import mx.itesm.appdibujandounmanana.*
import mx.itesm.appdibujandounmanana.databinding.FragmentNotificationsBinding
import mx.itesm.appdibujandounmanana.model.NombreData
import mx.itesm.appdibujandounmanana.ui.login.LoginActivity

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
        getUserName()
        registerObservers()
        registerEvents()
        return root
    }

    private fun getUserName(){
        //Obtener prefs de email registrado
        val preferencias = activity?.getSharedPreferences(PREFERENCES_ONBOARDING, AppCompatActivity.MODE_PRIVATE)
        val savedEmailPref = preferencias?.getString(KEY_EMAIL, "")
        if(savedEmailPref != null){
            notificationsViewModel.obtainUserNameData(NombreData(savedEmailPref))
        }else{
            println("oh no es nulo")
        }

    }

    private fun registerObservers(){
        notificationsViewModel.userName.observe(viewLifecycleOwner) {
            binding.userNameText.text = it
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
                binding.userNameText.visibility = View.VISIBLE
                binding.profileOrganizationText.visibility = View.GONE
                binding.profileIsUserLevelText.visibility = View.VISIBLE
            }else{
                binding.userNameText.visibility = View.GONE
                binding.profileOrganizationText.visibility = View.VISIBLE
                binding.profileIsUserLevelText.visibility = View.GONE
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
                .setTitle("Log out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes") { _, _ ->
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