package mx.itesm.appdibujandounmanana.ui.projects

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.appdibujandounmanana.R

class RegisterProjectFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterProjectFragment()
    }

    private lateinit var viewModel: RegisterProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_project_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterProjectViewModel::class.java)
        // TODO: Use the ViewModel
    }

}