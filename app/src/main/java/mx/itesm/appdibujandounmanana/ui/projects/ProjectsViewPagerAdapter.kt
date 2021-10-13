package mx.itesm.appdibujandounmanana.ui.projects

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

//borrar
class ProjectsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = listOf(
        AllProjectsFragment(),
        RegisterProjectFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                AllProjectsFragment()
            }
            1 -> {
                RegisterProjectFragment()
            }
            else -> {
                AllProjectsFragment()
            }
        }
    }


}