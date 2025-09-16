package com.example.library.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.library.databinding.ItemOnboardingBinding

class OnboardingAdapter(private val pages: List<OnboardingPage>)
    : RecyclerView.Adapter<OnboardingAdapter.VH>() {

    inner class VH(val binding: ItemOnboardingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = pages[position]
        with(holder.binding) {
            title.text = p.title
            subtitle.text = p.subtitle
            illustration.setImageResource(p.imageRes)  // SVG importado como vector
        }
    }

    override fun getItemCount() = pages.size
}
