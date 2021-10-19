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

class DonateTransactionFragment : Fragment() {

    private lateinit var binding: DonateTransactionFragmentBinding
    private lateinit var viewModel: DonateTransactionViewModel
    private lateinit var payPalButton: PayPalButton
    //private var asunto: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DonateTransactionFragmentBinding.inflate(layoutInflater)



        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        paypal()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun paypal(){
        payPalButton = requireView().findViewById<PayPalButton>(R.id.payPalButton)
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
                                "Donación"
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
}