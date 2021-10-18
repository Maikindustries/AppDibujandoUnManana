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
        configureEvents()
    }


    private fun configureEvents(){
        binding.mainFinancialModelBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_financialFragment)
        }

        binding.mainSocialInvestmentModelBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_socialInvestmentFragment)
        }

        binding.mainDumModelBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fundacionInvestmentFragment)
        }
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
        /*binding.mainLetsDonateButton.setOnClickListener{
            vibratePhone()
            findNavController().navigate(R.id.donateFragment)
        }*/
    }


    private fun fillRecyclerView(): ArrayList<HomeCardModel>{
        //Home Cards
        val infoCards: ArrayList<HomeCardModel> = ArrayList()
        infoCards.add(HomeCardModel("Educación", R.drawable.educacion, "Lograr una educación inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.", R.color.redDUM))
        infoCards.add(HomeCardModel("Salud", R.drawable.salud, "Garantizarles una vida sana y ejercer su bienestar físico y mental, mediante la inversión en proyectos de salud y discapacidad.", R.color.mustardDum))
        infoCards.add(HomeCardModel("Prevención", R.drawable.prevencion, "Promover las condiciones y los medios de vida adecuados, así como aumentar la cantidad y calidad de oportunidades para las niñas, niños y adolescentes.", R.color.teal_200))
        infoCards.add(HomeCardModel("Protección", R.drawable.proteccion, "Proporcionar protección especial, mediante cuidados especializados a las niñas, niños y adolescentes víctimas de omisión de cuidados, abandono, maltrato infantil y violencia.", R.color.grayDum))
        infoCards.add(HomeCardModel("Género", R.drawable.genero, "Empoderar a todas las niñas, así como promover la igualdad de las niñas, adolescentes y jóvenes mujeres. Esta causa engloba todas las anteriores.", R.color.orangeDUM))
        infoCards.add(HomeCardModel("Ayuda humanitaria", R.drawable.ayuda_humanitaria, "Brindar respuesta a emergencias a través de la ayuda humanitaria a los afectados por los desastres naturales.", R.color.redDUM))
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