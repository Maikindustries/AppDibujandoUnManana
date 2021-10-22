package mx.itesm.appdibujandounmanana.View

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import mx.itesm.appdibujandounmanana.View.Onboarding1Fragment
import mx.itesm.appdibujandounmanana.View.Onboarding2Fragment
import mx.itesm.appdibujandounmanana.View.Onboarding3Fragment

class ViewPagerAdapter (fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){

    private val fragments = listOf(
        Onboarding1Fragment(),
        Onboarding2Fragment(),
        Onboarding3Fragment()
    )

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}