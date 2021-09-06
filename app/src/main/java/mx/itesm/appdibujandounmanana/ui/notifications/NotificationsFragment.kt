package mx.itesm.appdibujandounmanana.ui.notifications

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
import mx.itesm.appdibujandounmanana.databinding.FragmentNotificationsBinding
import mx.itesm.appdibujandounmanana.ui.home.CardAdapter
import mx.itesm.appdibujandounmanana.ui.home.HomeCardModel

//user
class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        showBadgeCards()
        myDonationsButton()
        return root
    }


    fun myDonationsButton(){
        binding.profileMyDonationsBtn.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_myDonationsFragment)
        }
    }

    @SuppressLint("WrongConstant")
    fun showBadgeCards(){
        //Cards
        val badgeCards: ArrayList<BadgeCardModel> = ArrayList()
        for(i in 1..4){
            badgeCards.add(
                BadgeCardModel("Diamonod",
                    R.drawable.diamante,
                    "Donar millones",
                    1000000))
            badgeCards.add(
                BadgeCardModel("Gold",
                    R.drawable.oro,
                    "Donar cien miles",
                    100000))
        }

        binding.profileRecyclerView.layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL,false)
        binding.profileRecyclerView.adapter = BadgeAdapter(badgeCards)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}