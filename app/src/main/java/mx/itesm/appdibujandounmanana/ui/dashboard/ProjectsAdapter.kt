package mx.itesm.appdibujandounmanana.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.appdibujandounmanana.R

class ProjectsAdapter (val cards: ArrayList<PaymentCardModel>): RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.projects_title_text)
        val image: ImageView = itemView.findViewById(R.id.projects_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.special_projects_cards, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProjectsAdapter.ViewHolder, position: Int) {
        //Los valores de aqui dependen de las variables declaradas en la class ViewHolder
        val card = cards[position]
        holder.title.text = card.title
        holder.image.setImageResource(card.image)


    }

    override fun getItemCount(): Int = cards.size
}