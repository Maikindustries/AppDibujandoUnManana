package mx.itesm.appdibujandounmanana.ui.projects

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.databinding.AllProjectsFragmentBinding
import mx.itesm.appdibujandounmanana.ui.home.HomeCardListener
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import mx.itesm.appdibujandounmanana.R


class AllProjectsFragment : Fragment(),HomeCardListener {

    private lateinit var binding: AllProjectsFragmentBinding
    private lateinit var viewModel: AllProjectsViewModel
    private val allProjectsCardAdapter = AllProjectsCardAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AllProjectsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllProjectsViewModel::class.java)
        // TODO: Use the ViewModel
        configureRecycler()
    }

    private fun fillRecyclerView(): ArrayList<ProjectsCardModel>{
        //Home Cards
        val infoCards: ArrayList<ProjectsCardModel> = ArrayList()

        infoCards.add(ProjectsCardModel("Ayuda a Oaxaca", "Juntos por México", "3 months", 30, 100000,"Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",R.drawable.educacion))
        infoCards.add(ProjectsCardModel("Por los niños en EDOMEX", "Ayudemos hoy", "8 months", 12, 800000,"Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",R.drawable.genero))
        infoCards.add(ProjectsCardModel("El amor hacia los nuestros", "El amor", "12 months", 6, 50000,"Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",R.drawable.ayuda_humanitaria))

        return infoCards
    }

    private fun configureRecycler(){
        binding.allProjectsRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.allProjectsRecyclerview.adapter = allProjectsCardAdapter
        allProjectsCardAdapter.listener = this
    }

    //Evento (adaptador)
    override fun clickEnRenglon(position: Int) {
        val card = allProjectsCardAdapter.cards[position]
        //Pasar una clase creada del proyecto
        //val accion = AllProjectsFragmentDirections.actionAllProjectsFragmentToProjectInfoFragment(card)
        //findNavController().navigate(accion)
        
        //val intent = Intent(activity, LoginActivity::class.java)
        //startActivity(intent)

        //val fm  = parentFragmentManager.beginTransaction()
        //fm?.replace(R.id.allProjectsFragment, ProjectInfoFragment())
        //fm?.commit()
    }

}