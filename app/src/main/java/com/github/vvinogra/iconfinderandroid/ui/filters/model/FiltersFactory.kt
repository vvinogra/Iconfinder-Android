package com.github.vvinogra.iconfinderandroid.ui.filters.model

import com.github.vvinogra.iconfinderandroid.data.network.model.Category
import com.github.vvinogra.iconfinderandroid.data.network.model.Style
import javax.inject.Inject

class FiltersFactory @Inject constructor() {

    fun createStylesFilterSection(styles: List<Style>): FilterSection {
        return FilterSection(
            filters = styles.map { createStyleFilter(it) },
            filterType = FilterType.Styles
        )
    }

    fun createCategoriesFilterSection(categories: List<Category>): FilterSection {
        return FilterSection(
                filters = categories.map { createCategoryFilter(it) },
                filterType = FilterType.Categories
        )
    }

    private fun createCategoryFilter(category: Category): Filter {
        return Filter(
                name = category.name,
                identifier = category.identifier
        )
    }

    private fun createStyleFilter(style: Style): Filter {
        return Filter(
                name = style.name,
                identifier = style.identifier
        )
    }
}