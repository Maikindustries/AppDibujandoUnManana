package mx.itesm.appdibujandounmanana.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.KEY_EMAIL
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.model.DonationModel
import mx.itesm.appdibujandounmanana.model.MyDonationsViewModel
import mx.itesm.appdibujandounmanana.databinding.MyDonationsFragmentBinding
import mx.itesm.appdibujandounmanana.model.Correo

class MyDonationsFragment : Fragment(), DonationCardListener {


    private lateinit var myDonationsViewModel: MyDonationsViewModel
    private var _binding: MyDonationsFragmentBinding? = null
    private val donationAdapter = DonationAdapter(arrayListOf())
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myDonationsViewModel = ViewModelProvider(this).get(MyDonationsViewModel::class.java)
        _binding = MyDonationsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root


        configureRecyclerView()
        obtainDonations()
        registerObservers()
        return view
    }

    private fun registerObservers(){
        myDonationsViewModel.donationsArray.observe(viewLifecycleOwner) { lista ->
            donationAdapter.actualizar(lista)
        }
    }

    private fun obtainDonations(){
        val preferencias = activity?.getSharedPreferences(
            PREFERENCES_ONBOARDING,
            AppCompatActivity.MODE_PRIVATE
        )
        val savedPref = preferencias?.getString(KEY_EMAIL, "")
        if (savedPref != null){
            myDonationsViewModel.obtainDonations(Correo(savedPref))
        }
    }

    //Evento (adaptador)
    override fun clickEnRenglon(position: Int) {
        //enviar correo a dibujando un mañana para que le hagan su deducible
        notifyEmailSent()
    }

    private fun notifyEmailSent() {
        //si hay internet y/o fue exitoso al enviar el correo
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.deductible_message1))
            .setMessage(R.string.deductible_message2)
            .setPositiveButton("Ok") { _, _ ->}
        builder.show()
    }



    private fun configureRecyclerView(){
        binding.myDonationsRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.myDonationsRecyclerview.adapter = donationAdapter
        donationAdapter.listener = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}