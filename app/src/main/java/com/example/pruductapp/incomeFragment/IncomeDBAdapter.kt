package com.example.pruductapp.incomeFragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruductapp.IncomeExpenseDBModal
import com.example.pruductapp.R

class IncomeDBAdapter(var inExList: ArrayList<IncomeExpenseDBModal>) :
    RecyclerView.Adapter<IncomeDBAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtDate: TextView = itemView.findViewById(R.id.txtDate)
        var txt_type: TextView = itemView.findViewById(R.id.txt_type)
        var txtPaymentMethod: TextView = itemView.findViewById(R.id.txtPaymentMethod)
        var txtCategory: TextView = itemView.findViewById(R.id.txtCategory)
        var txtAmount: TextView = itemView.findViewById(R.id.txtAmount)
        var crdBackground: CardView =itemView.findViewById(R.id.crdBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View =
            (LayoutInflater.from(parent.context).inflate(R.layout.income_display_item, parent, false))
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var type = inExList.get(position).type

        if (type == 0) {

            holder.txtDate.text = inExList[position].date
            holder.txtPaymentMethod.text = inExList[position].payment
            holder.txtCategory.text = inExList[position].category
            holder.txtAmount.text = inExList[position].amount.toString()
            holder.txt_type.setText("Income")
            holder.crdBackground.setCardBackgroundColor(Color.GREEN)
            holder.txt_type.setTextColor(Color.GREEN)
        }
        else
        {
            holder.crdBackground.visibility= View.GONE
        }
    }

    override fun getItemCount(): Int {
        return inExList.size
    }
}