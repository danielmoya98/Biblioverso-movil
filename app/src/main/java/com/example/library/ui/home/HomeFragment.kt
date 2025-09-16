package com.example.library.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.library.R
import com.example.library.core.Prefs
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var shimmer: com.facebook.shimmer.ShimmerFrameLayout
    private lateinit var rv: RecyclerView
    private lateinit var emptyState: LinearLayout
    private val adapter = ReservaAdapter(emptyList())
    private val vm: ReservaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        shimmer = root.findViewById(R.id.shimmerReservas)
        rv = root.findViewById(R.id.rvReservas)
        emptyState = root.findViewById(R.id.viewEmptyReservas)

        rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rv.adapter = adapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recuperar usuario logueado desde Prefs
        val userId = Prefs.getUserId(requireContext())
        Log.d("HomeFragment", "ID de usuario obtenido de Prefs: $userId")

        if (userId != null) {
            vm.loadReservas(userId)
        } else {
            mostrarShimmer(false)
            rv.visibility = View.GONE
            emptyState.visibility = View.VISIBLE
        }

        // Observadores
        viewLifecycleOwner.lifecycleScope.launch {
            vm.loading.collect { mostrarShimmer(it) }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.reservas.collect { reservas ->
                if (reservas.isEmpty()) {
                    rv.visibility = View.GONE
                    emptyState.visibility = View.VISIBLE
                } else {
                    rv.visibility = View.VISIBLE
                    emptyState.visibility = View.GONE
                    adapter.updateData(reservas)
                }
            }
        }

        // Botones AppBar
        view.findViewById<View>(R.id.btnSearch).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        view.findViewById<View>(R.id.btnNotifications).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
        }
    }

    private fun mostrarShimmer(activo: Boolean) {
        if (activo) {
            shimmer.visibility = View.VISIBLE
            shimmer.startShimmer()
            rv.visibility = View.GONE
            emptyState.visibility = View.GONE
        } else {
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
        }
    }
}
