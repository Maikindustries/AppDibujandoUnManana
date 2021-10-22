package mx.itesm.appdibujandounmanana.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.model.OrganizationData

class AdminOrganizationCardAdapter (val cards: ArrayList<OrganizationData>): RecyclerView.Adapter<AdminOrganizationCardAdapter.ViewHolder>() {

    var listener: AdminOrganizationCardListener? = null //referencia de un supertipo

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.proposed_org_title_text)
        val tag: TextView = itemView.findViewById(R.id.proposed_org_tag_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.row_proposed_organization, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Los valores de aqui dependen de las variables declaradas en la class ViewHolder
        val card = cards[position]
        holder.title.text = card.nombre
        holder.tag.text = card.tag

        val vista = holder.itemView
        val layoutRenglon = vista.findViewById<Button>(R.id.proposed_org_more_btn)
        layoutRenglon.setOnClickListener {
            listener?.clickEnRenglon(position)
        }
    }

    override fun getItemCount(): Int = cards.size

    fun actualizar(lista: ArrayList<OrganizationData>?) {
        cards.clear()//liberar memoria
        if(lista!=null){
            cards.addAll(lista)
        }
        notifyDataSetChanged() //Recargar la informacion
    }

}