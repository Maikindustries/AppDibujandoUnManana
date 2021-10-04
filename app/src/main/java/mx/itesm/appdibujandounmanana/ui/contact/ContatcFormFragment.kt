package mx.itesm.appdibujandounmanana.ui.contact

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.appdibujandounmanana.R

class ContatcFormFragment : Fragment() {

    companion object {
        fun newInstance() = ContatcFormFragment()
    }

    private lateinit var viewModel: ContatcFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contatc_form_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContatcFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}