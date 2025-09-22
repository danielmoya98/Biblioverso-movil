package com.example.library.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.library.R
import com.example.library.data.dto.FavoritoDto

class FavoritoAdapter(
    private var items: List<FavoritoDto>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_BOOK = 0
    private val TYPE_DISCOVER = 1

    override fun getItemViewType(position: Int): Int {
        return if (position < items.size) TYPE_BOOK else TYPE_DISCOVER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_BOOK) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorito_book, parent, false)
            FavoritoViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorito_discover, parent, false)
            DiscoverViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoritoViewHolder && position < items.size) {
            val item = items[position]
            holder.titulo.text = item.titulo
            holder.sinopsis.text = item.sinopsis
            Glide.with(holder.itemView)
                .load(item.portada)
                .placeholder(R.drawable.bg_img)
                .into(holder.img)
        }
    }

    override fun getItemCount(): Int {
        // máximo 4 items + 1 discover
        val limited = items.take(4)
        return limited.size + 1
    }

    fun updateData(newItems: List<FavoritoDto>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class FavoritoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgPortadaFav)
        val titulo: TextView = view.findViewById(R.id.tvTituloFav)
        val sinopsis: TextView = view.findViewById(R.id.tvSinopsisFav)
    }

    inner class DiscoverViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
