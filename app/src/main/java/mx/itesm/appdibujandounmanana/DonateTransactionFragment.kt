package mx.itesm.appdibujandounmanana

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DonateTransactionFragment : Fragment() {

    companion object {
        fun newInstance() = DonateTransactionFragment()
    }

    private lateinit var viewModel: DonateTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.donate_transaction_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DonateTransactionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}