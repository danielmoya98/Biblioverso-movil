package com.example.library.ui.search

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.library.R
import com.example.library.data.dto.CategoriaDto

class CategoriaAdapter(
    private var categorias: List<CategoriaDto>,
    private val onClick: (CategoriaDto) -> Unit,
    private val context: Context
) : RecyclerView.Adapter<CategoriaAdapter.CategoriaVH>() {

    private var selectedPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoria_text, parent, false)
        return CategoriaVH(view)
    }

    override fun onBindViewHolder(holder: CategoriaVH, position: Int) {
        val categoria = categorias[position]
        holder.tv.text = categoria.nombre

        if (position == selectedPos) {
            holder.tv.setTextColor(context.getColor(R.color.black))
            animateTextSize(holder.tv, 14f, 16f)

            holder.indicator.visibility = View.VISIBLE
            holder.indicator.animate().alpha(1f).setDuration(200).start()
        } else {
            holder.tv.setTextColor(context.getColor(R.color.teal_700))
            animateTextSize(holder.tv, 16f, 14f)

            holder.indicator.animate().alpha(0f).setDuration(200).withEndAction {
                holder.indicator.visibility = View.INVISIBLE
            }.start()
        }

        holder.itemView.setOnClickListener {
            val old = selectedPos
            selectedPos = position
            notifyItemChanged(old)
            notifyItemChanged(position)
            onClick(categoria)
        }
    }

    override fun getItemCount() = categorias.size

    fun updateData(newCategorias: List<CategoriaDto>) {
        categorias = newCategorias
        notifyDataSetChanged()
    }

    private fun animateTextSize(tv: TextView, from: Float, to: Float) {
        val anim = ValueAnimator.ofFloat(from, to)
        anim.duration = 200
        anim.addUpdateListener { valueAnimator ->
            tv.textSize = valueAnimator.animatedValue as Float
        }
        anim.start()
    }

    inner class CategoriaVH(view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.tvCategoria)
        val indicator: View = view.findViewById(R.id.viewIndicator)
    }
}
