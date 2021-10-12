package mx.itesm.appdibujandounmanana.ui.mydonations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.appdibujandounmanana.databinding.MyDonationsFragmentBinding

class MyDonationsFragment : Fragment() {


    private lateinit var myDonationsViewModel: MyDonationsViewModel
    private var _binding: MyDonationsFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myDonationsViewModel = ViewModelProvider(this).get(MyDonationsViewModel::class.java)
        _binding = MyDonationsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root




        showDonationCards()
        return view
    }

    fun showDonationCards(){
        //Donations
        val posts: ArrayList<DonationModel> = ArrayList()
        for (i in 1..10) {
            posts.add(DonationModel("Regalos con causa","\$$i", "$i/12/2020"))
        }
        for (i in 1..4) {
            posts.add(DonationModel("Ayuda a la CDMX","\$$i", "1$i/12/2020"))
        }

        binding.myDonationsRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.myDonationsRecyclerview.adapter = DonationAdapter(posts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}