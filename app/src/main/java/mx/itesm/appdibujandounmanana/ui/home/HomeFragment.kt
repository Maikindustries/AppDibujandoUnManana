package mx.itesm.appdibujandounmanana.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root



        showInfoCards()
        redirectDonateButton()
        return root
    }


    private fun redirectDonateButton(){
        binding.mainLetsDonateButton.setOnClickListener{
            findNavController().navigate(R.id.donateFragment)
        }
    }

    @SuppressLint("WrongConstant")
    fun showInfoCards(){
        //Cards
        val infoCards: ArrayList<HomeCardModel> = ArrayList()
        for(i in 1..4){
            infoCards.add(
                HomeCardModel("Educación $i",
                R.drawable.dumlogo))
            infoCards.add(
                HomeCardModel("Comida $i",
                    R.drawable.logoblancofinal))
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL,false)
        binding.recyclerView.adapter = CardAdapter(infoCards)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}