package mx.itesm.appdibujandounmanana.Admin.AdminProjects

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.AdminProjectsInfoFragmentBinding

class AdminProjectsInfoFragment : Fragment() {


    private lateinit var viewModel: AdminProjectsInfoViewModel
    private lateinit var binding: AdminProjectsInfoFragmentBinding
    private val args: AdminProjectsInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AdminProjectsInfoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminProjectsInfoViewModel::class.java)
        // TODO: Use the ViewModel
        fillView()
        registerEvents()
    }

    private fun fillView(){
        binding.adminProjectInfoProjectNameText.text = args.selectedProject.projectTitle
        binding.adminProjectInfoDescriptionText.text = args.selectedProject.description
        binding.adminProjectInfoOrganizationNameText.text = args.selectedProject.organization
        binding.adminProjectInfoProjectImage.setImageResource(args.selectedProject.projectImage)

        binding.adminProjectInfoCollapsingToolbar.title = args.selectedProject.projectTitle
        val money = args.selectedProject.money
        val numWorkers = args.selectedProject.numberWorkers
    }

    private fun registerEvents(){
        //accept button
        binding.adminProjectInfoAcceptBtn.setOnClickListener {
            Toast.makeText(activity, "Project approved", Toast.LENGTH_SHORT).show()
            //project is approved
            //project appears in user interface
            //project dissappears from admin interface
            findNavController().navigateUp()
        }
        //reject button
        binding.adminProjectInfoRejectBtn.setOnClickListener {
            Toast.makeText(activity, "Project rejected", Toast.LENGTH_SHORT).show()
            //project is deleted from database
            //send email, your project was not approved
            findNavController().navigateUp()
        }
    }

}