package mx.itesm.appdibujandounmanana.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.KEY_ONBOARDING_INICIATED
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ProjectsFragmentBinding
import mx.itesm.appdibujandounmanana.ViewModel.ProjectsViewModel

class ProjectsFragment : Fragment(), HomeCardListener {


    private lateinit var viewModel: ProjectsViewModel
    private lateinit var binding: ProjectsFragmentBinding
    private val allProjectsCardAdapter = AllProjectsCardAdapter(arrayListOf())

    //private lateinit var tabLayout: TabLayout
    //private lateinit var viewPager2: ViewPager2
    //private lateinit var adapter: ProjectsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProjectsFragmentBinding.inflate(layoutInflater)


        isOrganization()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectsViewModel::class.java)
        // TODO: Use the ViewModel
        configureRecycler()
        configureEvents()
        obtainApprovedProjects()
        configureObservers()
        //configureViewPager2()

        //val fragmentTransaction = parentFragmentManager.beginTransaction()
        //  fragmentTransaction.add(R.id.fragment_container_view_tag, ProjectInfoFragment()).commit()
    }

    private fun isOrganization(){
        val preferencias = activity?.getSharedPreferences(
            PREFERENCES_ONBOARDING,
            AppCompatActivity.MODE_PRIVATE
        )
        val savedPref = preferencias?.getInt(KEY_ONBOARDING_INICIATED, -1)
        if (savedPref != null){
            if (savedPref == 2){
                binding.projectsRegisterProjectsBtn.visibility = View.GONE
            }else{
                binding.projectsRegisterProjectsBtn.visibility = View.VISIBLE
            }
        }
    }

    private fun configureObservers(){
        viewModel.projectsArray.observe(viewLifecycleOwner) {
            allProjectsCardAdapter.actualizar(it)
        }
    }

    private fun obtainApprovedProjects(){
        viewModel.obtainApprovedProjectsData()

    }

    /*private fun fillRecyclerView(): ArrayList<ProjectData> {
        //Home Cards
        val infoCards: ArrayList<ProjectsCardModel> = ArrayList()

        infoCards.add(
            ProjectsCardModel(
                "Ayuda a Oaxaca",
                "Juntos por M??xico",
                "3 months",
                30,
                100000,
                "Lograr una educaci??n inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",
                R.drawable.educacion
            )
        )
        infoCards.add(
            ProjectsCardModel(
                "Por los ni??os en EDOMEX",
                "Ayudemos hoy",
                "8 months",
                12,
                800000,
                "Lograr una educaci??n inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",
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
                "Lograr una educaci??n inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.",
                R.drawable.ayuda_humanitaria
            )
        )

        return infoCards
    }*/

    private fun configureRecycler() {
        binding.allProjectsRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.allProjectsRecyclerview.adapter = allProjectsCardAdapter
        allProjectsCardAdapter.listener = this
    }

    //Evento (adaptador)
    override fun clickEnRenglon(position: Int) {
        val card = allProjectsCardAdapter.cards[position]

        //Pasar una clase creada del proyecto
        val accion = ProjectsFragmentDirections.actionProjectsFragmentToProjectInfoFragment2(card)
        findNavController().navigate(accion)
    }

    private fun configureEvents() {
        binding.projectsRegisterProjectsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_projectsFragment_to_registerProjectFragment)
        }
    }


    /*private fun configureViewPager2(){
        //Configure viewpager2 and tablayout
        tabLayout = binding.projectsTabLayout
        viewPager2 = binding.projectsViewPager
        adapter = ProjectsViewPagerAdapter(parentFragmentManager,lifecycle)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2){tab, position ->
            when(position){
                0 -> {
                    tab.text = "All Projects"
                }
                1 -> {
                    tab.text = "Register project"
                }
            }
        }.attach()
    }*/
}