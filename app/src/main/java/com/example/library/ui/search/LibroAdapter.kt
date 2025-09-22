package com.example.library.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.library.R
import com.example.library.data.dto.LibroDto

class LibroAdapter(
    private var libros: List<LibroDto>
) : RecyclerView.Adapter<LibroAdapter.LibroVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_libro, parent, false)
        return LibroVH(view)
    }

    override fun onBindViewHolder(holder: LibroVH, position: Int) {
        val libro = libros[position]
        holder.titulo.text = libro.titulo
        holder.categoria.text = libro.categoria
        Glide.with(holder.itemView)
            .load(libro.portada)
            .placeholder(R.drawable.bg_img)
            .into(holder.img)
    }

    override fun getItemCount() = libros.size

    fun updateData(newItems: List<LibroDto>) {
        libros = newItems
        notifyDataSetChanged()
    }

    inner class LibroVH(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgLibro)
        val titulo: TextView = view.findViewById(R.id.tvTituloLibro)
        val categoria: TextView = view.findViewById(R.id.tvCategoriaLibro)
    }
}