package mx.itesm.appdibujandounmanana.Admin.AdminOrganizations

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.appdibujandounmanana.R

class AdminOrganizationInfoFragment : Fragment() {

    companion object {
        fun newInstance() = AdminOrganizationInfoFragment()
    }

    private lateinit var viewModel: AdminOrganizationInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.admin_organization_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdminOrganizationInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}