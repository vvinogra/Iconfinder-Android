package com.github.vvinogra.iconfinderandroid.ui.filters.presentation

import android.content.res.Resources
import com.github.vvinogra.iconfinderandroid.ui.filters.model.FilterSection
import javax.inject.Inject

class FiltersPresentationFactory @Inject constructor(
        private val resources: Resources
) {
        fun createPricesFilterSectionPresentation(filterSection: FilterSection): FilterSectionPresentation {
            return FilterSectionPresentation(
//                    filterSection
            )
        }
}