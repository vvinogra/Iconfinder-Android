package com.github.vvinogra.iconfinderandroid.ui.searchicons.presentation

data class IconPresentation(
    val previewUrl: String
)

data class ResultsFiltersPresentation(
    val approximateResultText: String
)

data class SearchIconsPresentation(
        val icons: List<IconPresentation>,
        val resultsFiltersPresentation: ResultsFiltersPresentation
)
