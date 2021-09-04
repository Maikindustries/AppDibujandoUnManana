package mx.itesm.appdibujandounmanana.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.FragmentDashboardBinding

//donate
class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root



        showPaymentCards1()
        showPaymentCards2()
        return root
    }


    @SuppressLint("WrongConstant")
    fun showPaymentCards1(){
        //Cards
        val paymentCards: ArrayList<PaymentCardModel> = ArrayList()
        for(i in 1..4){
            paymentCards.add(
                PaymentCardModel("mensuales",
                    R.drawable.noventaynueve,
                    99))
            paymentCards.add(
                PaymentCardModel("mensuales",
                    R.drawable.cien,
                    100)
            )
        }

        binding.paymentRecyclerView.layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL,false)
        binding.paymentRecyclerView.adapter = PaymentAdapter(paymentCards)
    }

    @SuppressLint("WrongConstant")
    fun showPaymentCards2(){
        //Cards
        val paymentCards: ArrayList<PaymentCardModel> = ArrayList()
        for(i in 1..4){
            paymentCards.add(
                PaymentCardModel("mensuales",
                    R.drawable.noventaynueve,
                    99))
            paymentCards.add(
                PaymentCardModel("mensuales",
                    R.drawable.cien,
                    100)
            )
        }

        binding.paymentRecyclerView2.layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL,false)
        binding.paymentRecyclerView2.adapter = PaymentAdapter(paymentCards)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}