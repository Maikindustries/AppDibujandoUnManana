package mx.itesm.appdibujandounmanana.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.FragmentDashboardBinding
import mx.itesm.appdibujandounmanana.ui.home.HomeFragmentDirections

//donate
class DashboardFragment : Fragment(), PaymentCardListener {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null
    private var paymentCardAdapter = PaymentAdapter(fillRecyclerView())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var vibrator: Vibrator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        configureRecyclerView()
        fillRecyclerView()
        //showPaymentCards2()
        donateButton()

        return root
    }

    fun Fragment.vibratePhone(){
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(90, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(20)
        }
    }

    private fun donateButton(){
        binding.donateDonateNowButton.setOnClickListener {
            vibratePhone()
            findNavController().navigate(R.id.action_navigation_donate_to_donateTransactionFragment)
        }
    }


    @SuppressLint("WrongConstant")
    fun configureRecyclerView(){
        binding.paymentRecyclerView.layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL,false)
        binding.paymentRecyclerView.adapter = paymentCardAdapter
        paymentCardAdapter.listener = this
    }

    //Evento (adaptador)
    override fun clickEnRenglon(position: Int) {
        val card = paymentCardAdapter.cards[position]

        //Pasar una clase creada del proyecto
        //val accion = DashboardFragmentDirections.actionNavigationDonateToDonateTransactionFragment(card)
        findNavController().navigate(R.id.action_navigation_donate_to_donateTransactionFragment)
    }

    private fun fillRecyclerView():ArrayList<PaymentCardModel> {
        //Cards
        val paymentCards: ArrayList<PaymentCardModel> = ArrayList()
        for(i in 1..1){
            paymentCards.add(
                PaymentCardModel("mensuales",
                    R.drawable.noventaynueve,
                    49))
            paymentCards.add(
                PaymentCardModel("mensuales",
                    R.drawable.noventaynueve,
                    99))
            paymentCards.add(
                PaymentCardModel("mensuales",
                    R.drawable.cien,
                    149)
            )
        }
        return paymentCards

    }

    /*@SuppressLint("WrongConstant")
    fun showPaymentCards2(){
        //Cards
        val paymentCards: ArrayList<PaymentCardModel> = ArrayList()
        for(i in 1..1){
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
        binding.paymentRecyclerView2.adapter = ProjectsAdapter(paymentCards)
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}