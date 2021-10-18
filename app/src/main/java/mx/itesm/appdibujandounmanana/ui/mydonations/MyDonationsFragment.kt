package mx.itesm.appdibujandounmanana.ui.mydonations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.KEY_EMAIL
import mx.itesm.appdibujandounmanana.KEY_ONBOARDING_INICIATED
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.databinding.MyDonationsFragmentBinding
import mx.itesm.appdibujandounmanana.model.Correo
import mx.itesm.appdibujandounmanana.ui.home.CardAdapter
import mx.itesm.appdibujandounmanana.ui.home.HomeCardModel
import mx.itesm.appdibujandounmanana.ui.home.HomeFragmentDirections

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
            .setTitle("Deductible petition was correctly asked.")
            .setMessage("In 3-5 days, we will contact you")
            .setPositiveButton("Ok") { _, _ ->}
        builder.show()

        //si no hay internet o fallo el envío del correo
        /*val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Deductible petition couldn´t be asked.")
            .setMessage("Please verify your internet connection and try again.")
            .setPositiveButton("Ok") { _, _ ->}
        builder.show()*/
    }

    private fun fillRecyclerView(): ArrayList<DonationModel>{
        //Donations
        val donations: ArrayList<DonationModel> = ArrayList()
        for (i in 1..10) {
            donations.add(DonationModel("Regalos con causa","\$$i", "$i/12/2020"))
        }
        for (i in 1..4) {
            donations.add(DonationModel("Ayuda a la CDMX","\$$i", "1$i/12/2020"))
        }
        return donations
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