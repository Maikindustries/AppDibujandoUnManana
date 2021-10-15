package mx.itesm.appdibujandounmanana.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.Order
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PayPalButton
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

        val payPalButton = requireView().findViewById<PayPalButton>(R.id.payPalButton)

        payPalButton.setup(
            createOrder = CreateOrder { createOrderActions ->
                val order = Order(
                    intent = OrderIntent.CAPTURE,
                    appContext = AppContext(
                        userAction = UserAction.PAY_NOW
                    ),
                    purchaseUnitList = listOf(
                        PurchaseUnit(
                            amount = Amount(
                                currencyCode = CurrencyCode.USD,
                                value = "10.00"
                            )
                        )
                    )
                )

                createOrderActions.create(order)
            },
            onApprove = OnApprove { approval ->
                approval.orderActions.capture { captureOrderResult ->
                    Log.i("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
                    println("CaptureOrderResult: $captureOrderResult")
                }
            },
            onError = OnError { errorInfo ->
                Log.d("OnError", "Error: $errorInfo")
            }
        )

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