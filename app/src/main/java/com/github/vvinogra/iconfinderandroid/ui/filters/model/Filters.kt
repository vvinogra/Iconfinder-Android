package com.github.vvinogra.iconfinderandroid.ui.filters.model

enum class FilterType {
    Categories,
    Styles,
    Prices,
    Licences;
}

data class FilterSection(
        val filters: List<Filter>,
        val filterType: FilterType
)

data class Filter(
        val name: String,
        val identifier: String
)