package mx.itesm.appdibujandounmanana.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.DonateTransactionFragmentBinding

class DonateTransactionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: DonateTransactionFragmentBinding
    private lateinit var viewModel: DonateTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DonateTransactionFragmentBinding.inflate(layoutInflater)



        val lista = resources.getStringArray(R.array.dedication)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DonateTransactionViewModel::class.java)
        // TODO: Use the ViewModel


    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        println("aaaaaaaaaaaaaaaaaaaaa")
        println(p0)
        println(p1)
        println(p2)
        if (binding.donationSpinner.selectedItem.toString() == "Another"){

            binding.donationDetailsAnotherDedicationEditText.visibility = View.VISIBLE
        }else{
            binding.donationDetailsAnotherDedicationEditText.visibility = View.GONE
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
        println("se cambio el estado")
    }

}