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
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.ViewModel.AdminOrganizationInfoViewModel
import mx.itesm.appdibujandounmanana.databinding.AdminOrganizationInfoFragmentBinding
import mx.itesm.appdibujandounmanana.model.OrganizationData

class AdminOrganizationInfoFragment : Fragment() {

    private lateinit var viewModel: AdminOrganizationInfoViewModel
    private lateinit var binding: AdminOrganizationInfoFragmentBinding
    private val args: AdminOrganizationInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AdminOrganizationInfoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminOrganizationInfoViewModel::class.java)
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

    private fun registerEvents() {
        binding.adminOrganizationInfoAcceptBtn.setOnClickListener {
            viewModel.approveOrganization(
                OrganizationData(
                    args.selectedOrganization.nombre, args.selectedOrganization.tag,
                    args.selectedOrganization.description, args.selectedOrganization.password,
                    args.selectedOrganization.phone, args.selectedOrganization.email
                )
            )

            Handler(Looper.getMainLooper()).postDelayed({
                if (viewModel.isSuccessfulApprove.value != null) {
                    if (viewModel.isSuccessfulApprove.value == true) {
                        Toast.makeText(activity, R.string.organization_approved, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(activity, R.string.unsuccessful_petition, Toast.LENGTH_SHORT)
                            .show()
                    }
                    findNavController().navigateUp()
                }
            }, 300)
        }
        binding.adminOrganizationInfoRejectBtn.setOnClickListener {
            viewModel.rejectOrganization(
                OrganizationData(
                    args.selectedOrganization.nombre, args.selectedOrganization.tag,
                    args.selectedOrganization.description, args.selectedOrganization.password,
                    args.selectedOrganization.phone, args.selectedOrganization.email
                )
            )

            Handler(Looper.getMainLooper()).postDelayed({
                if (viewModel.isSuccessfulRejection.value != null) {
                    if (viewModel.isSuccessfulRejection.value == true) {
                        Toast.makeText(activity, R.string.organization_rejected, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(activity, R.string.unsuccessful_petition, Toast.LENGTH_SHORT)
                            .show()
                    }
                    findNavController().navigateUp()
                }
            }, 300)
        }
    }

    private fun fillView() {
        binding.adminOrganizationInfoOrganizationNameText.text = args.selectedOrganization.nombre
        binding.adminOrganizationInfoTagText.text = args.selectedOrganization.tag
        binding.adminOrganizationInfoNumberText.text = args.selectedOrganization.phone
        binding.adminOrganizationInfoEmailText.text = args.selectedOrganization.email
        binding.adminOrganizationInfoDescriptionText.text = args.selectedOrganization.description
    }

}