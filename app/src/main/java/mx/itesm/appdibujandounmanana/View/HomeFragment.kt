package mx.itesm.appdibujandounmanana.View

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
import mx.itesm.appdibujandounmanana.ViewModel.HomeViewModel
import mx.itesm.appdibujandounmanana.databinding.FragmentHomeBinding
import mx.itesm.appdibujandounmanana.ui.home.HomeCardModel
import java.util.*
import kotlin.collections.ArrayList

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
        if(Locale.getDefault().displayLanguage == "espa??ol"){
            infoCards.add(HomeCardModel("Educaci??n", R.drawable.educacion, "Lograr una educaci??n inclusiva y de calidad, como herramienta que les permita contar con las competencias necesarias para un desarrollo sostenible.", R.color.redDUM))
            infoCards.add(HomeCardModel("Salud", R.drawable.salud,"Garantizarles una vida sana y ejercer su bienestar f??sico y mental, mediante la inversi??n en proyectos de salud y discapacidad.", R.color.mustardDum))
            infoCards.add(HomeCardModel("Prevenci??n", R.drawable.prevencion, "Promover las condiciones y los medios de vida adecuados, as?? como aumentar la cantidad y calidad de oportunidades para las ni??as, ni??os y adolescentes.", R.color.teal_200))
            infoCards.add(HomeCardModel("Protecci??n", R.drawable.proteccion, "Proporcionar protecci??n especial, mediante cuidados especializados a las ni??as, ni??os y adolescentes v??ctimas de omisi??n de cuidados, abandono, maltrato infantil y violencia.", R.color.grayDum))
            infoCards.add(HomeCardModel("G??nero", R.drawable.genero, "Empoderar a todas las ni??as, as?? como promover la igualdad de las ni??as, adolescentes y j??venes mujeres. Esta causa engloba todas las anteriores.", R.color.orangeDUM))
            infoCards.add(HomeCardModel("Ayuda humanitaria", R.drawable.ayuda_humanitaria, "Proporcionar una respuesta de emergencia a trav??s de la ayuda humanitaria a los afectados por las cat??strofes naturales.", R.color.redDUM))
        }else{
            infoCards.add(HomeCardModel("Education", R.drawable.educacion, "Achieve inclusive and quality education, as a tool that allows them to have the necessary skills for sustainable development", R.color.redDUM))
            infoCards.add(HomeCardModel("Health", R.drawable.salud,"Guarantee them a healthy life and exercise their physical and mental well-being, by investing in health and disability projects.", R.color.mustardDum))
            infoCards.add(HomeCardModel("Prevention", R.drawable.prevencion, "Promote adequate conditions and means of life, as well as increase the quantity and quality of opportunities for girls, boys and adolescents.", R.color.teal_200))
            infoCards.add(HomeCardModel("Protection", R.drawable.proteccion, "Provide special protection, through specialized care, to girls, boys and adolescents who are victims of neglect of care, abandonment, child abuse and violence.", R.color.grayDum))
            infoCards.add(HomeCardModel("Gender", R.drawable.genero, "Empower all girls, as well as promote equality for girls, adolescents and young women. This cause encompasses all of the above.", R.color.orangeDUM))
            infoCards.add(HomeCardModel("Humanitarian aid", R.drawable.ayuda_humanitaria, "Provide emergency response through humanitarian aid to those affected by natural disasters.", R.color.redDUM))
        }

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