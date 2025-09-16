package com.example.library.ui.reservations


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.library.R
import com.google.android.material.chip.ChipGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest

class ReservationsFragment : Fragment(R.layout.fragment_reservations) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val chips = view.findViewById<ChipGroup>(R.id.chips)
        val rv = view.findViewById<RecyclerView>(R.id.rv)

    }
}