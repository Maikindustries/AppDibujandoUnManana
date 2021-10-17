package mx.itesm.appdibujandounmanana.Admin.AdminOrganizations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.Admin.AdminProjects.AdminProjectsFragmentDirections
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.AdminVerifyOrganizationFragmentBinding
import mx.itesm.appdibujandounmanana.model.OrganizationData
import mx.itesm.appdibujandounmanana.ui.projects.ProjectsCardModel

class AdminVerifyOrganizationFragment : Fragment(), AdminOrganizationCardListener {

    private lateinit var viewModel: AdminVerifyOrganizationViewModel
    private lateinit var binding: AdminVerifyOrganizationFragmentBinding
    private val adminVerifyCardAdapter = AdminOrganizationCardAdapter(fillRecyclerView())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.admin_verify_organization_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminVerifyOrganizationViewModel::class.java)
        // TODO: Use the ViewModel
        configureRecycler()
    }

    private fun fillRecyclerView(): ArrayList<OrganizationData> {
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
    }

    private fun configureRecycler() {
        binding.adminOrganizationRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.adminOrganizationRecyclerview.adapter = adminVerifyCardAdapter
        adminVerifyCardAdapter.listener = this
    }

    override fun clickEnRenglon(position: Int) {
        val card = adminVerifyCardAdapter.cards[position]

        //Pasar una clase creada del proyecto
        //val accion = AdminProjectsFragmentDirections.actionAdminProjectsFragmentToAdminProjectsInfoFragment(card)
        //findNavController().navigate(accion)
    }

}