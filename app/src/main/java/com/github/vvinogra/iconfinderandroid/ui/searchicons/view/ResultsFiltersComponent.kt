package com.github.vvinogra.iconfinderandroid.ui.searchicons.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.github.vvinogra.iconfinderandroid.R
import com.github.vvinogra.iconfinderandroid.databinding.ResultsFiltersComponentBinding
import com.github.vvinogra.iconfinderandroid.ui.searchicons.presentation.ResultsFiltersPresentation

class ResultsFiltersComponent @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ResultsFiltersComponentBinding = ResultsFiltersComponentBinding.inflate(
            LayoutInflater.from(context), this, true)

    init {
        binding.filtersBtn.setOnClickListener {
            findNavController().navigate(R.id.action_search_icons_fragment_to_filtersFragment)
        }
    }

    fun applyPresentation(resultsFiltersPresentation: ResultsFiltersPresentation) {
        binding.resultsTv.text = resultsFiltersPresentation.approximateResultText
    }
}