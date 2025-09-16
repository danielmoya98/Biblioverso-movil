package com.example.library.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.library.R
import com.example.library.data.dto.ReservaDto

class ReservaAdapter(
    private var items: List<ReservaDto>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_RESERVA = 0
        private const val TYPE_DISCOVER_MORE = 1
    }

    inner class ReservaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgPortada)
        val titulo: TextView = view.findViewById(R.id.tvTitulo)
        val autor: TextView = view.findViewById(R.id.tvAutor)

        fun bind(reserva: ReservaDto) {
            titulo.text = reserva.titulo
            autor.text = reserva.autor
            Glide.with(itemView)
                .load(reserva.portada)
                .placeholder(R.drawable.bg_img)
                .into(img)
        }
    }

    inner class DiscoverMoreViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemViewType(position: Int): Int {
        return if (position == items.size) TYPE_DISCOVER_MORE else TYPE_RESERVA
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_RESERVA) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_reserva_book, parent, false)
            ReservaViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_discover_more, parent, false)
            DiscoverMoreViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ReservaViewHolder && position < items.size) {
            holder.bind(items[position])
        } else if (holder is DiscoverMoreViewHolder) {
            holder.itemView.setOnClickListener {
                // Aquí defines qué pasa cuando se toca "Discover More"
                Toast.makeText(holder.itemView.context, "Descubrir más libros...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = items.size + 1 // suma 1 para "Discover More"

    fun updateData(newItems: List<ReservaDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}
