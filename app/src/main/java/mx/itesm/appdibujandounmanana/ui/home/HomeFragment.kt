package mx.itesm.appdibujandounmanana.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), HomeCardListener {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val homeCardAdapter = CardAdapter(fillRecyclerView())

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

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        returnButton()
        redirectDonateButton()
        configureRecyclerView()
    }


    //Evento (adaptador)
    override fun clickEnRenglon(position: Int) {
        val card = homeCardAdapter.cards[position]

        //Pasar una clase creada del proyecto
        val accion = HomeFragmentDirections.actionHomeFragmentToDUMCausesFragment(card)
        findNavController().navigate(accion)
    }


    private fun returnButton(){
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            activity?.finish()//terminar esta actividad
        }
        callback.isEnabled
    }


    fun Fragment.vibratePhone(){
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(90, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(90)
        }
    }


    private fun redirectDonateButton(){
        binding.mainLetsDonateButton.setOnClickListener{
            vibratePhone()
            findNavController().navigate(R.id.donateFragment)
        }
    }



    private fun fillRecyclerView(): ArrayList<HomeCardModel>{
        //Home Cards
        val infoCards: ArrayList<HomeCardModel> = ArrayList()
        infoCards.add(HomeCardModel("Educación", R.drawable.educacion))
        infoCards.add(HomeCardModel("Salud", R.drawable.salud))
        infoCards.add(HomeCardModel("Prevención", R.drawable.prevencion))
        infoCards.add(HomeCardModel("Protección", R.drawable.proteccion))
        infoCards.add(HomeCardModel("Género", R.drawable.genero))
        infoCards.add(HomeCardModel("Ayuda humanitaria", R.drawable.ayuda_humanitaria))
        return infoCards
    }


    @SuppressLint("WrongConstant")
    fun configureRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, OrientationHelper.HORIZONTAL,false)
        binding.recyclerView.adapter = homeCardAdapter
        homeCardAdapter.listener = this
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}