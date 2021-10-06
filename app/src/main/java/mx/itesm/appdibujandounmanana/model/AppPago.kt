package mx.itesm.appdibujandounmanana.model

import android.app.Application
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction
import mx.itesm.appdibujandounmanana.BuildConfig

class AppPago : Application() {
    private val CLIENT_ID: String = "AZOEusUQN4gY1Iq0VQvmHK1qR5M-0VD6PlgwVnxob8SPVJYz1dnKDCEumK0QMfAq2N6beOn24teTOiR6"

    override fun onCreate() {
        super.onCreate()
        val config = CheckoutConfig(
            application = this,
            clientId = CLIENT_ID,
            environment = Environment.SANDBOX,
            returnUrl = "${BuildConfig.APPLICATION_ID}://paypalpay",
            currencyCode = CurrencyCode.MXN,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )
        PayPalCheckout.setConfig(config)
    }
}