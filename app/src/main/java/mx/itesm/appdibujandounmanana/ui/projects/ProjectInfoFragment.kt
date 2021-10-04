package mx.itesm.appdibujandounmanana.ui.projects

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ProjectInfoFragmentBinding
import mx.itesm.appdibujandounmanana.ui.home.DUMCausesFragmentArgs

class ProjectInfoFragment : Fragment() {

    private lateinit var viewModel: ProjectInfoViewModel
    private lateinit var binding: ProjectInfoFragmentBinding
    private val args: ProjectInfoFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProjectInfoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProjectInfoViewModel::class.java)
        // TODO: Use the ViewModel
        //fillView()
    }

    /*private fun fillView(){
        binding.projectInfoProjectNameText.text = args.selectedProject.projectTitle
        binding.projectInfoDescriptionText.text = args.selectedProject.description
        binding.projectInfoTimeText.text = args.selectedProject.time
        binding.projectInfoOrganizationNameText.text = args.selectedProject.organization
        binding.projectInfoProjectImage.setImageResource(args.selectedProject.projectImage)

        val money = args.selectedProject.money
        val numWorkers = args.selectedProject.numberWorkers
    }*/

}