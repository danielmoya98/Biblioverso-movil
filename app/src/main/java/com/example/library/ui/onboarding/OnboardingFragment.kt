package com.example.library.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.library.R
import com.example.library.core.Prefs
import com.example.library.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingBinding.bind(view)

        val pages = listOf(
            OnboardingPage(
                R.drawable.library_amico,
                getString(R.string.onb_title_welcome),
                getString(R.string.onb_sub_welcome)
            ),
            OnboardingPage(
                R.drawable.library_rafiki,
                getString(R.string.onb_title_env),
                getString(R.string.onb_sub_env)
            ),
            OnboardingPage(
                R.drawable.book_lover_pana,
                getString(R.string.onb_title_team),
                getString(R.string.onb_sub_team)
            )
        )

        val adapter = OnboardingAdapter(pages)
        binding.pager.adapter = adapter
        binding.pager.offscreenPageLimit = 1

        // Peek lateral + sin glow
        val peek = resources.getDimensionPixelSize(R.dimen.onb_page_peek)
        binding.pager.setPadding(peek, 0, peek, 0)
        binding.pager.clipToPadding = false
        binding.pager.clipChildren = false
        (binding.pager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        // Margen entre páginas
        val marginPx = resources.getDimensionPixelSize(R.dimen.onb_page_margin)
        binding.pager.setPageTransformer(
            CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(marginPx))
            }
        )

        // Dots
        binding.dotsIndicator.attachTo(binding.pager)

        // Botones
        binding.btnSkipText.setOnClickListener { finishOnboarding() }
        binding.btnNextText.setOnClickListener {
            val pos = binding.pager.currentItem
            if (pos < pages.lastIndex) binding.pager.currentItem = pos + 1
        }
        binding.btnGetStarted.setOnClickListener { finishOnboarding() }

        fun updateButtons(pos: Int) {
            val last = pos == pages.lastIndex
            binding.btnSkipText.isVisible = !last
            binding.btnNextText.isVisible = !last
            binding.btnGetStarted.isVisible = last
        }
        updateButtons(0)

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) = updateButtons(position)
        })
    }

    private fun finishOnboarding() {
        try { Prefs.setFirstRun(requireContext(), false) } catch (_: Throwable) {}
        findNavController().navigate(R.id.welcomeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
