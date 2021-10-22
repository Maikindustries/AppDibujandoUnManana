package mx.itesm.appdibujandounmanana.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import mx.itesm.appdibujandounmanana.databinding.FragmentDUMCausesBinding


class DUMCausesFragment : Fragment() {

    private lateinit var binding: FragmentDUMCausesBinding
    private val args: DUMCausesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDUMCausesBinding.inflate(layoutInflater)


        fillView()
        return binding.root
    }


    private fun fillView(){
        binding.causesCollapsingToolbar.title = args.selectedCause.title
        binding.dumCausesImage.setImageResource(args.selectedCause.image)
        binding.dumCausesDescriptionText.text = args.selectedCause.description
    }
}