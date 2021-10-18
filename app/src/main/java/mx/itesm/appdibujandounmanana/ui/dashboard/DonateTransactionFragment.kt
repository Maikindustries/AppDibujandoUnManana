package mx.itesm.appdibujandounmanana.ui.dashboard

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.RequiresApi
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
import mx.itesm.appdibujandounmanana.model.DonacionData
import mx.itesm.appdibujandounmanana.model.JsonDonacionData
import java.time.LocalDateTime

class DonateTransactionFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: DonateTransactionFragmentBinding
    private lateinit var viewModel: DonateTransactionViewModel
    private var asunto: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DonateTransactionFragmentBinding.inflate(layoutInflater)



        val lista = resources.getStringArray(R.array.dedication)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
                                currencyCode = CurrencyCode.MXN,
                                value = binding.donationDetailsAmountEditText.text.toString()
                            )
                        )
                    )
                )

                createOrderActions.create(order)
            },
            onApprove = OnApprove { approval ->
                approval.orderActions.capture { captureOrderResult ->
                    viewModel.postDonacion(
                        JsonDonacionData(
                        DonacionData(
                            LocalDateTime.now().toString(),
                            binding.donationDetailsAmountEditText.text.toString(),
                            asunto
                        )
                    )
                    )
                    Log.i("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
                    println("CaptureOrderResult: $captureOrderResult")
                }
            },
            onError = OnError { errorInfo ->
                Log.d("OnError", "Error: $errorInfo")
            }
        )

        viewModel = ViewModelProvider(this).get(DonateTransactionViewModel::class.java)


    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        asunto = p0?.getItemAtPosition(p2).toString()
        /*println("aaaaaaaaaaaaaaaaaaaaa")
        println(p0)
        println(p1)
        println(p2)
        if (binding.donationSpinner.selectedItem.toString() == "Another"){

            binding.donationDetailsAnotherDedicationEditText.visibility = View.VISIBLE
        }else{
            binding.donationDetailsAnotherDedicationEditText.visibility = View.GONE
        }*/

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
        println("se cambio el estado")
    }

}