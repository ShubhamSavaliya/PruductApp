package com.example.pruductapp

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class IncomeExpenseDBAdapter(var incExpList: ArrayList<IncomeExpenseDBModal>) :
    RecyclerView.Adapter<IncomeExpenseDBAdapter.MyHolderView>() {
    class MyHolderView(iniView: View) : RecyclerView.ViewHolder(iniView) {
        val txtDate: TextView = itemView.findViewById(R.id.txtDate)
        val txtPaymentType: TextView = itemView.findViewById(R.id.txtPaymentMethod)
        val txtCategory: TextView = itemView.findViewById(R.id.txtCategory)
        val txtAmount: TextView = itemView.findViewById(R.id.txtAmount)
        val txt_type: TextView = itemView.findViewById(R.id.txt_type)
        var crdBackground:CardView=itemView.findViewById(R.id.crdBackground)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderView {
        val v: View =
            (LayoutInflater.from(parent.context).inflate(R.layout.income_display_item, parent, false))
        return MyHolderView(v)
    }

    override fun onBindViewHolder(holder: MyHolderView, position: Int) {

        holder.txtDate.text = incExpList[position].date
        holder.txtPaymentType.text = incExpList[position].payment
        holder.txtCategory.text = incExpList[position].category
        holder.txtAmount.text = incExpList[position].amount.toString()

        var type=incExpList.get(position).type.toInt()
        if(type==0){
            holder.txt_type.setText("Income")
            holder.txt_type.setTextColor(Color.GREEN)
            holder.crdBackground.setCardBackgroundColor(Color.GREEN)
        }else{
            holder.txt_type.setText("Expense")
            holder.txt_type.setTextColor(Color.RED)
            holder.crdBackground.setCardBackgroundColor(Color.RED)
        }

    }

    override fun getItemCount(): Int {
        return incExpList.size
    }
}