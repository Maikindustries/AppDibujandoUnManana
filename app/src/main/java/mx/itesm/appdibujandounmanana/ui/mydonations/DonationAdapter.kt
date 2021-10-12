package mx.itesm.appdibujandounmanana.ui.mydonations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.appdibujandounmanana.R

class DonationAdapter (val cards: ArrayList<DonationModel>): RecyclerView.Adapter<DonationAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val description: TextView = itemView.findViewById(R.id.myDonations_description_text)
        val money: TextView = itemView.findViewById(R.id.myDonations_money_text)
        val date: TextView = itemView.findViewById(R.id.myDonations_date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_donation, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonationAdapter.ViewHolder, position: Int) {
        //Los valores de aqui dependen de las variables declaradas en la class ViewHolder
        val card = cards[position]
        holder.description.text = card.description
        holder.money.text = card.money
        holder.date.text = card.date

        //holder.title.text = cards[position]

    }

    override fun getItemCount(): Int = cards.size
}