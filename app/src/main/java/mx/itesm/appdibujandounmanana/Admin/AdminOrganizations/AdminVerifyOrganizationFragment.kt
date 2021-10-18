package mx.itesm.appdibujandounmanana.Admin.AdminOrganizations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.databinding.AdminVerifyOrganizationFragmentBinding

class AdminVerifyOrganizationFragment : Fragment(), AdminOrganizationCardListener {

    private lateinit var viewModel: AdminVerifyOrganizationViewModel
    private lateinit var binding: AdminVerifyOrganizationFragmentBinding
    private val adminVerifyCardAdapter = AdminOrganizationCardAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AdminVerifyOrganizationFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminVerifyOrganizationViewModel::class.java)
        // TODO: Use the ViewModel
        configureRecycler()
        configureObservers()
        obtainProjectsForAproval()
    }

    private fun configureObservers(){
        viewModel.organizationsArray.observe(viewLifecycleOwner) {
            adminVerifyCardAdapter.actualizar(it)
        }
    }

    private fun obtainProjectsForAproval(){
        viewModel.obtainOrganizationsDataForAproval()
    }

    /*private fun fillRecyclerView(): ArrayList<OrganizationData> {
        //Home Cards
        val infoCards: ArrayList<OrganizationData> = ArrayList()

        infoCards.add(
            OrganizationData(
                "Amor a México","Alimentación",
                "Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",
                "unu","5512212121", "amor@hotmail.com"))

        infoCards.add(
            OrganizationData(
                "Amor a Yucatán","Inundación",
                "Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",
                "unu","5512212121", "amor2@hotmail.com"))

        return infoCards
    }*/

    private fun configureRecycler() {
        binding.adminVerifyOrganizationRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.adminVerifyOrganizationRecyclerview.adapter = adminVerifyCardAdapter
        adminVerifyCardAdapter.listener = this
    }

    override fun clickEnRenglon(position: Int) {
        val card = adminVerifyCardAdapter.cards[position]

        //Pasar una clase creada del proyecto
        val accion = AdminVerifyOrganizationFragmentDirections.actionAdminVerifyOrganizationFragmentToAdminOrganizationInfoFragment(card)
        findNavController().navigate(accion)
    }

}