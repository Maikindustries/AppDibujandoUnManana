package mx.itesm.appdibujandounmanana.ui.projects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.ui.dashboard.PaymentCardModel
import mx.itesm.appdibujandounmanana.ui.home.HomeCardListener

class AllProjectsCardAdapter (val cards: ArrayList<ProjectsCardModel>): RecyclerView.Adapter<AllProjectsCardAdapter.ViewHolder>() {
    //fragmento
    var listener: HomeCardListener? = null //referencia de un supertipo

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.projects_title_text)
        val image: ImageView = itemView.findViewById(R.id.projects_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.special_projects_cards, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllProjectsCardAdapter.ViewHolder, position: Int) {
        //Los valores de aqui dependen de las variables declaradas en la class ViewHolder
        val card = cards[position]
        holder.title.text = card.projectTitle
        holder.image.setImageResource(card.projectImage)

        val vista = holder.itemView
        val layoutRenglon = vista.findViewById<LinearLayout>(R.id.card_special_projects_click)
        layoutRenglon.setOnClickListener {
            listener?.clickEnRenglon(position)
        }
    }

    override fun getItemCount(): Int = cards.size
}