package com.example.library.ui.search

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.library.R
import com.example.library.core.ApiClient
import com.example.library.data.api.CategoriaApi
import com.example.library.data.dto.CategoriaDto
import com.example.library.data.dto.LibroDto
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var libroAdapter: LibroAdapter
    private lateinit var api: CategoriaApi

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvCategorias = view.findViewById<RecyclerView>(R.id.rvCategorias)
        val rvResultados = view.findViewById<RecyclerView>(R.id.rvResultados)
        val tvCategoryTitle = view.findViewById<TextView>(R.id.tvCategoryTitle)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPagerCarousel)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabIndicators)

        // ✅ Inicializar API
        api = ApiClient.retrofit.create(CategoriaApi::class.java)

        // Adapters
        categoriaAdapter = CategoriaAdapter(emptyList(), { categoria ->
            tvCategoryTitle.text = "Categoría: ${categoria.nombre}"
            loadLibrosPorCategoria(categoria.id_categoria)
        }, requireContext())


        libroAdapter = LibroAdapter(emptyList())

        // Layouts
        rvCategorias.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategorias.adapter = categoriaAdapter

        rvResultados.layoutManager = GridLayoutManager(requireContext(), 2)
        rvResultados.adapter = libroAdapter

        // 🔄 Cargar categorías desde API
        loadCategorias()

        // Carousel (banners estáticos por ahora)
        val images = listOf(
            R.drawable.banner,
            R.drawable.banner,
            R.drawable.banner
        )
        val carouselAdapter = CarouselAdapter(images)
        viewPager.adapter = carouselAdapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        (viewPager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        // Auto-scroll
        lifecycleScope.launch {
            while (true) {
                delay(3000)
                val next = (viewPager.currentItem + 1) % images.size
                viewPager.setCurrentItem(next, true)
            }
        }

        // Botón volver (si lo usas en AppBar o en el layout)
        view.findViewById<MaterialButton>(R.id.btnBackToHome)?.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }
    }

    // 🔹 Llamar API: Categorías
    private fun loadCategorias() {
        lifecycleScope.launch {
            try {
                val categorias = api.getCategorias()
                categoriaAdapter.updateData(categorias)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // 🔹 Llamar API: Libros por Categoría
    private fun loadLibrosPorCategoria(idCategoria: Int) {
        lifecycleScope.launch {
            try {
                val libros = api.getLibrosPorCategoria(idCategoria)
                libroAdapter.updateData(libros)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
