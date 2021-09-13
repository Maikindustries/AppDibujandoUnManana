package mx.itesm.appdibujandounmanana.ui.contact

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.databinding.ContactFragmentBinding
import mx.itesm.appdibujandounmanana.databinding.FragmentHomeBinding
import mx.itesm.appdibujandounmanana.ui.home.HomeViewModel

class ContactFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel
    private var _binding: ContactFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactViewModel =
            ViewModelProvider(this).get(ContactViewModel::class.java)
        _binding = ContactFragmentBinding.inflate(inflater, container, false)





        return binding.root
    }

    fun Fragment.vibratePhone(){
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(90, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(20)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        // TODO: Use the ViewModel
        makeCall()
    }

    fun makeCall(){

        binding.contactPhoneOneBtn.setOnClickListener {
            vibratePhone()
            val callIntent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:55 21 22 52 86"))
            startActivity(callIntent)
        }
        binding.contactPhoneTwoBtn.setOnClickListener {
            vibratePhone()
            val callIntent: Intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:81 44 44 86 43"))
            startActivity(callIntent)
        }

        binding.contactEmailOneBtn.setOnClickListener {
            vibratePhone()
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("contacto@dibujando.org.mx")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Contact CDMX (name)")
                putExtra(Intent.EXTRA_TEXT, "Hello, I'm interested in contributing to the organization.")
                //putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
            }
            startActivity(intent)
        }

        binding.contactEmailTwoBtn.setOnClickListener {
            vibratePhone()
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("gdi.mty@dibujando.org.mx")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "Contact MTY (name)")
                putExtra(Intent.EXTRA_TEXT, "Hello, I'm interested in contributing to the organization.")
                //putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
            }
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}