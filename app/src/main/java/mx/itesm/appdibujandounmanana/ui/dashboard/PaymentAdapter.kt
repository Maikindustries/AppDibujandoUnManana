package mx.itesm.appdibujandounmanana.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.appdibujandounmanana.R

class PaymentAdapter (val cards: ArrayList<PaymentCardModel>): RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.payment_title_text)
        val image: ImageView = itemView.findViewById(R.id.payment_card_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.payment_cards, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentAdapter.ViewHolder, position: Int) {
        //Los valores de aqui dependen de las variables declaradas en la class ViewHolder
        val card = cards[position]
        holder.title.text = card.title
        holder.image.setImageResource(card.image)


    }

    override fun getItemCount(): Int = cards.size
}