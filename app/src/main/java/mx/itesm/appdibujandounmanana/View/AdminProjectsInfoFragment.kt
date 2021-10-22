package mx.itesm.appdibujandounmanana.View

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import mx.itesm.appdibujandounmanana.ViewModel.AdminProjectsInfoViewModel
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.AdminProjectsInfoFragmentBinding
import mx.itesm.appdibujandounmanana.model.ProjectData

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
        registerObservers()
    }

    private fun registerObservers() {
        viewModel.isSuccessfulApprove.observe(viewLifecycleOwner) {
            it
        }
        viewModel.isSuccessfulRejection.observe(viewLifecycleOwner) {
            it
        }
    }

    private fun fillView() {
        binding.adminProjectInfoProjectNameText.text = args.selectedProject.name
        binding.adminProjectInfoDescriptionText.text = args.selectedProject.description
        binding.adminProjectInfoOrganizationNameText.text = args.selectedProject.organization
        binding.adminProjectInfoProjectImage.setImageResource(R.drawable.salud)
        binding.adminProjectInfoCollapsingToolbar.title = args.selectedProject.name
    }

    private fun registerEvents() {
        //accept button
        binding.adminProjectInfoAcceptBtn.setOnClickListener {
            /*println(args.selectedProject.name)
            println(args.selectedProject.description)
            println(args.selectedProject.organization)*/
            viewModel.approveProject(
                ProjectData(
                    args.selectedProject.name,
                    args.selectedProject.description, args.selectedProject.organization
                )
            )//aqui da nul la organizacion

            Handler(Looper.getMainLooper()).postDelayed({
                if (viewModel.isSuccessfulApprove.value != null) {
                    if (viewModel.isSuccessfulApprove.value == true) {
                        Toast.makeText(activity, R.string.project_approved, Toast.LENGTH_SHORT).show()
                    } else
                        Toast.makeText(
                            activity,
                            R.string.unsuccessful_petition,
                            Toast.LENGTH_SHORT
                        ).show()
                    findNavController().navigateUp()
                }
            }, 300)
        }
        //reject button
        binding.adminProjectInfoRejectBtn.setOnClickListener {
            viewModel.rejectProject(
                ProjectData(
                    args.selectedProject.name,
                    args.selectedProject.description, args.selectedProject.organization
                )
            )//tambien aqui da null

            Handler(Looper.getMainLooper()).postDelayed({
                if (viewModel.isSuccessfulRejection.value != null) {
                    if (viewModel.isSuccessfulRejection.value == true) {
                        Toast.makeText(activity, R.string.project_rejected, Toast.LENGTH_SHORT).show()
                    } else
                        Toast.makeText(
                            activity,
                            R.string.unsuccessful_petition,
                            Toast.LENGTH_SHORT
                        ).show()
                    findNavController().navigateUp()
                }
            }, 300)
        }
    }
}