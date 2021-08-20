package com.github.vvinogra.iconfinderandroid.ui.filters.presentation

//data class FiltersPresentation(
//        val filterSections: List<FilterSectionPresentation>
//)

//typealias

data class FilterSectionPresentation(
        val filters: List<FilterPresentation>,
        val header: String,
        val selectedFilterId: String,
        val noFiltersSelectedFilter: FilterPresentation
)

data class FilterPresentation(
        val displayName: String,
        val identifier: String
)