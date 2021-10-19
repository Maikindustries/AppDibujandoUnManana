package mx.itesm.appdibujandounmanana.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.appdibujandounmanana.R

class CardAdapter (val cards: ArrayList<HomeCardModel>): RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    //fragmento
    var listener: HomeCardListener? = null //referencia de un supertipo

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.card_title_text)
        val image: ImageView = itemView.findViewById(R.id.card_image)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.card_linear_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_cards, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardAdapter.ViewHolder, position: Int) {
        //Los valores de aqui dependen de las variables declaradas en la class ViewHolder
        val card = cards[position]
        holder.title.text = card.title
        holder.image.setImageResource(card.image)
        holder.linearLayout.setBackgroundResource(card.color)

        /*val card = cards[position]
        holder.title.text = card.title
        holder.image.setImageResource(card.image)
        //holder.linearLayout.setBackgroundResource(card.color)
        holder.linearLayout.background.setTint(0xffffff)*/

        val vista = holder.itemView
        val layoutRenglon = vista.findViewById<LinearLayout>(R.id.card_linear_layout)
        layoutRenglon.setOnClickListener {
            listener?.clickEnRenglon(position)
        }

    }

    override fun getItemCount(): Int = cards.size
}