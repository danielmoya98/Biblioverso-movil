package com.example.library.ui.bookmarks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.library.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.flow.collectLatest

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}