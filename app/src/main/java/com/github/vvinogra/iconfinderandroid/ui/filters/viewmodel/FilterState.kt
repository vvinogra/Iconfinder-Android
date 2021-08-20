package com.github.vvinogra.iconfinderandroid.ui.filters.viewmodel

import com.github.vvinogra.iconfinderandroid.ui.filters.presentation.FilterSectionPresentation

//sealed class FiltersState {
//    object Loading : FiltersState()
//    data class Success(val filtersPresentation: FiltersPresentation) : FiltersState()
//    data class Failure(val failure: Throwable) : FiltersState()
//}

sealed class FilterState {
    object Empty : FilterState()
    object Loading : FilterState()
    data class Success(val filterSectionPresentation: FilterSectionPresentation) : FilterState()
    data class Failure(val failure: Throwable) : FilterState()
}