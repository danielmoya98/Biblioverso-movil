package com.example.library.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.library.R
import com.example.library.data.dto.ReservaDto

class ReservaAdapter(
    private var items: List<ReservaDto>
) : RecyclerView.Adapter<ReservaAdapter.ReservaViewHolder>() {

    inner class ReservaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgPortada)
        val titulo: TextView = view.findViewById(R.id.tvTitulo)
        val autor: TextView = view.findViewById(R.id.tvAutor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reserva_book, parent, false)
        return ReservaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReservaViewHolder, position: Int) {
        val item = items[position]
        holder.titulo.text = item.titulo
        holder.autor.text = item.autor
        Glide.with(holder.itemView)
            .load(item.portada)
            .placeholder(R.drawable.bg_img)
            .into(holder.img)
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<ReservaDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}
