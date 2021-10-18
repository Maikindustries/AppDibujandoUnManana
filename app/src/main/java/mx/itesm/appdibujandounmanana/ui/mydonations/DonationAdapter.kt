package mx.itesm.appdibujandounmanana.ui.mydonations

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.itesm.appdibujandounmanana.R
import mx.itesm.appdibujandounmanana.model.DonacionData
import mx.itesm.appdibujandounmanana.ui.home.HomeCardListener

class DonationAdapter (val cards: ArrayList<DonacionData>): RecyclerView.Adapter<DonationAdapter.ViewHolder>() {

    //fragmento
    var listener: DonationCardListener? = null //referencia de un supertipo

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
        holder.description.text = card.descripcion
        holder.money.text = "\$${card.monto}"
        holder.date.text = card.fecha
        val vista = holder.itemView
        val layoutRenglon = vista.findViewById<Button>(R.id.myDonations_deductible_btn)
        layoutRenglon.setOnClickListener {
            listener?.clickEnRenglon(position)
        }

    }

    override fun getItemCount(): Int = cards.size

    fun actualizar(lista: ArrayList<DonacionData>?) {
        cards.clear()//liberar memoria
        if(lista!=null){
            cards.addAll(lista)
        }
        notifyDataSetChanged() //Recargar la informacion
    }
}