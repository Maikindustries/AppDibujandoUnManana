package mx.itesm.appdibujandounmanana.ui.projects

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ProjectsFragmentBinding
import mx.itesm.appdibujandounmanana.ui.introduction.ViewPagerAdapter

class ProjectsFragment : Fragment() {



    private lateinit var viewModel: ProjectsViewModel
    private lateinit var binding: ProjectsFragmentBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: ProjectsViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProjectsFragmentBinding.inflate(layoutInflater)



        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectsViewModel::class.java)
        // TODO: Use the ViewModel
        configureViewPager2()

    //val fragmentTransaction = parentFragmentManager.beginTransaction()
      //  fragmentTransaction.add(R.id.fragment_container_view_tag, ProjectInfoFragment()).commit()
    }



    private fun configureViewPager2(){
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
    }
}