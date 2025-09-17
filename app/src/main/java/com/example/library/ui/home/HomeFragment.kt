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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.library.R
import com.example.library.core.Prefs
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    // Reservas
    private lateinit var shimmerReservas: com.facebook.shimmer.ShimmerFrameLayout
    private lateinit var rvReservas: RecyclerView
    private lateinit var emptyReservas: LinearLayout
    private val reservasAdapter = ReservaAdapter(emptyList())
    private val reservasVM: ReservaViewModel by viewModels()

    // Favoritos
    private lateinit var shimmerFavoritos: com.facebook.shimmer.ShimmerFrameLayout
    private lateinit var rvFavoritos: RecyclerView
    private lateinit var emptyFavoritos: LinearLayout
    private val favoritosAdapter = FavoritoAdapter(emptyList())
    private val favoritosVM: FavoritoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Reservas
        shimmerReservas = root.findViewById(R.id.shimmerReservas)
        rvReservas = root.findViewById(R.id.rvReservas)
        emptyReservas = root.findViewById(R.id.viewEmptyReservas)
        rvReservas.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvReservas.adapter = reservasAdapter

        // Favoritos
        shimmerFavoritos = root.findViewById(R.id.shimmerFavoritos)
        rvFavoritos = root.findViewById(R.id.rvFavoritos)
        emptyFavoritos = root.findViewById(R.id.viewEmptyFavoritos)
        rvFavoritos.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        rvFavoritos.adapter = favoritosAdapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = Prefs.getUserId(requireContext())
        Log.d("HomeFragment", "ID de usuario obtenido de Prefs: $userId")

        if (userId != null) {
            reservasVM.loadReservas(userId)
            favoritosVM.loadFavoritos(userId)
        }

        // Observadores Reservas
        viewLifecycleOwner.lifecycleScope.launch {
            reservasVM.loading.collect { mostrarShimmerReservas(it) }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            reservasVM.reservas.collect { reservas ->
                if (reservas.isEmpty()) {
                    rvReservas.visibility = View.GONE
                    emptyReservas.visibility = View.VISIBLE
                } else {
                    rvReservas.visibility = View.VISIBLE
                    emptyReservas.visibility = View.GONE
                    reservasAdapter.updateData(reservas)
                }
            }
        }

        // Observadores Favoritos
        viewLifecycleOwner.lifecycleScope.launch {
            favoritosVM.loading.collect { mostrarShimmerFavoritos(it) }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            favoritosVM.favoritos.collect { favoritos ->
                if (favoritos.isEmpty()) {
                    rvFavoritos.visibility = View.GONE
                    emptyFavoritos.visibility = View.VISIBLE
                } else {
                    rvFavoritos.visibility = View.VISIBLE
                    emptyFavoritos.visibility = View.GONE
                    favoritosAdapter.updateData(favoritos)
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

    private fun mostrarShimmerReservas(activo: Boolean) {
        if (activo) {
            shimmerReservas.visibility = View.VISIBLE
            shimmerReservas.startShimmer()
            rvReservas.visibility = View.GONE
            emptyReservas.visibility = View.GONE
        } else {
            shimmerReservas.stopShimmer()
            shimmerReservas.visibility = View.GONE
        }
    }

    private fun mostrarShimmerFavoritos(activo: Boolean) {
        if (activo) {
            shimmerFavoritos.visibility = View.VISIBLE
            shimmerFavoritos.startShimmer()
            rvFavoritos.visibility = View.GONE
            emptyFavoritos.visibility = View.GONE
        } else {
            shimmerFavoritos.stopShimmer()
            shimmerFavoritos.visibility = View.GONE
        }
    }
}
