package mx.itesm.appdibujandounmanana.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.ViewModel.AdminProjectsViewModel
import mx.itesm.appdibujandounmanana.databinding.AdminProjectsFragmentBinding

class AdminProjectsFragment : Fragment(), AdminProjectsCardListener {


    private lateinit var viewModel: AdminProjectsViewModel
    private lateinit var binding: AdminProjectsFragmentBinding
    private val adminProjectsCardAdapter = AdminProjectsCardAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AdminProjectsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminProjectsViewModel::class.java)
        configureRecycler()
        configureObservers()
        obtainProjectsForAproval()
    }

    private fun configureObservers(){
        viewModel.projectsArray.observe(viewLifecycleOwner) {
            adminProjectsCardAdapter.actualizar(it)
        }
    }

    private fun obtainProjectsForAproval(){
        viewModel.obtainProjectsDataForAproval()
    }

    private fun configureRecycler() {
        binding.adminProjectsRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.adminProjectsRecyclerview.adapter = adminProjectsCardAdapter
        adminProjectsCardAdapter.listener = this
    }

    override fun clickEnRenglon(position: Int) {
        val card = adminProjectsCardAdapter.cards[position]

        //Pasar una clase creada del proyecto
        val accion = AdminProjectsFragmentDirections.actionAdminProjectsFragmentToAdminProjectsInfoFragment(card)
        findNavController().navigate(accion)
    }

}