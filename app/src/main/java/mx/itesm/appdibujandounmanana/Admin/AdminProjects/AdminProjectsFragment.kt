package mx.itesm.appdibujandounmanana.Admin.AdminProjects

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.AdminProjectsFragmentBinding
import mx.itesm.appdibujandounmanana.ui.projects.ProjectsCardModel
import mx.itesm.appdibujandounmanana.ui.projects.ProjectsFragmentDirections

class AdminProjectsFragment : Fragment(), AdminProjectsCardListener {


    private lateinit var viewModel: AdminProjectsViewModel
    private lateinit var binding: AdminProjectsFragmentBinding
    private val adminProjectsCardAdapter = AdminProjectsCardAdapter(fillRecyclerView())

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
        // TODO: Use the ViewModel
        configureRecycler()
    }


    private fun fillRecyclerView(): ArrayList<ProjectsCardModel> {
        //Home Cards
        val infoCards: ArrayList<ProjectsCardModel> = ArrayList()

        infoCards.add(
            ProjectsCardModel(
                "Ayuda a Oaxaca",
                "Juntos por México",
                "3 months",
                30,
                100000,
                "Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",
                R.drawable.educacion
            )
        )
        infoCards.add(
            ProjectsCardModel(
                "Por los niños en EDOMEX",
                "Ayudemos hoy",
                "8 months",
                12,
                800000,
                "Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",
                R.drawable.genero
            )
        )
        infoCards.add(
            ProjectsCardModel(
                "El amor hacia los nuestros",
                "El amor",
                "12 months",
                6,
                50000,
                "Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",
                R.drawable.ayuda_humanitaria
            )
        )

        return infoCards
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