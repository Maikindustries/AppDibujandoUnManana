package mx.itesm.appdibujandounmanana.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.appdibujandounmanana.R

class BadgeAdapter (val cards: ArrayList<BadgeCardModel>): RecyclerView.Adapter<BadgeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.donate_badge_card_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.badge_cards, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeAdapter.ViewHolder, position: Int) {
        //Los valores de aqui dependen de las variables declaradas en la class ViewHolder
        val card = cards[position]
        holder.image.setImageResource(card.image)
    }

    override fun getItemCount(): Int = cards.size
}