package mx.itesm.appdibujandounmanana.View

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
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
import mx.itesm.appdibujandounmanana.KEY_EMAIL
import mx.itesm.appdibujandounmanana.PREFERENCES_ONBOARDING
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.SignInDonateFragmentBinding
import mx.itesm.appdibujandounmanana.model.DonacionData
import mx.itesm.appdibujandounmanana.model.JsonDonacionData
import mx.itesm.appdibujandounmanana.ViewModel.SignInDonateViewModel
import java.time.LocalDateTime

class SignInDonateFragment : Fragment() {

    private lateinit var binding: SignInDonateFragmentBinding
    private lateinit var viewModel: SignInDonateViewModel
    private lateinit var payPalButton: PayPalButton
    //private var asunto: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SignInDonateFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SignInDonateViewModel::class.java)

        returnButton()
        paypal()
    }


    private fun verifyIdentity() {

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun paypal() {
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


                    if (binding.donationDetailsDedicationEditText.text.toString().isNotEmpty()) {
                        viewModel.postDonacion(
                            DonacionData(
                                LocalDateTime.now().toString(),
                                binding.donationDetailsAmountEditText.text.toString(),
                                binding.donationDetailsDedicationEditText.text.toString(),
                                "Sin cuenta"
                            )
                        )
                    } else {
                        viewModel.postDonacion(
                            DonacionData(
                                LocalDateTime.now().toString(),
                                binding.donationDetailsAmountEditText.text.toString(),
                                "Donación", "Sin cuenta"
                            )
                        )
                    }




                    Log.i("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
                    println("CaptureOrderResult: $captureOrderResult")
                }
            },
            onError = OnError { errorInfo ->
                Log.d("OnError", "Error: $errorInfo")
            }
        )
    }

    private fun returnButton() {
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigate(R.id.signInFrag)
        }
        callback.isEnabled
    }

}