package com.example.library.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.library.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.fragment.findNavController

class BookDetailFragment : Fragment(R.layout.fragment_book_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getString("bookId").orEmpty()

    }
}