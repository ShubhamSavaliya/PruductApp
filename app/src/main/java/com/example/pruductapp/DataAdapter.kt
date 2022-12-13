package com.example.pruductapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(
    var context: Context,
    var list: ArrayList<DatabaseModelClass>,
    var editInterface: EditInterface,
    var deleteItem: ((Int) -> Unit)
) :
    RecyclerView.Adapter<DataAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var txtId = view.findViewById<TextView>(R.id.txtId)
        var txtName = view.findViewById<TextView>(R.id.txtName)

        var imgEdit = view.findViewById<LinearLayout>(R.id.imgEdit)
        var imgDelete = view.findViewById<LinearLayout>(R.id.imgDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(context).inflate(R.layout.table_display_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.txtId.setText(list.get(position).Id.toString())
        holder.txtName.setText(list.get(position).Name)

        holder.imgEdit.setOnClickListener {

            editInterface.intentcall(
                context,
                list.get(position).Id,
                list.get(position).Name
            )
        }

        holder.imgDelete.setOnClickListener {

            var dialog = Dialog(context)
            dialog.setContentView(R.layout.delete_dialog);
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false);

            var btnDelete = dialog.findViewById<Button>(R.id.btnDelete)
            var btnCencel = dialog.findViewById<Button>(R.id.btnCencel)

            btnDelete.setOnClickListener {
                deleteItem.invoke(list[position].Id)
                dialog.dismiss()
            }
            btnCencel.setOnClickListener {
                dialog.dismiss() }
            dialog.show()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}