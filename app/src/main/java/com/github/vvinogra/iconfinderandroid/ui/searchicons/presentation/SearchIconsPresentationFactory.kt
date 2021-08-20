package com.github.vvinogra.iconfinderandroid.ui.searchicons.presentation

import android.content.res.Resources
import com.github.vvinogra.iconfinderandroid.R
import com.github.vvinogra.iconfinderandroid.data.network.model.Icon
import com.github.vvinogra.iconfinderandroid.data.network.model.SearchIconsResponse
import java.text.NumberFormat
import javax.inject.Inject

class SearchIconsPresentationFactory @Inject constructor(
        private val numberFormat: NumberFormat,
        private val resources: Resources
) {
    fun createSearchIconsPresentation(searchIconsResponse: SearchIconsResponse) : SearchIconsPresentation {
        return SearchIconsPresentation(
                icons = createIconPresentationList(searchIconsResponse.icons),
                resultsFiltersPresentation = createResultsFiltersPresentation(searchIconsResponse.totalCount)
        )
    }

    private fun createIconPresentationList(iconList: List<Icon>) = iconList.map {
        createIconPresentation(it)
    }

    private fun createIconPresentation(icon: Icon) = IconPresentation(
            previewUrl = icon.rasterSizes.last().formats.first().previewUrl
    )

    private fun createResultsFiltersPresentation(totalItemCount: Int) = ResultsFiltersPresentation(
            approximateResultText = resources.getString(
                    R.string.results_filters_component_about_number_icons, numberFormat.format(totalItemCount))
    )
}