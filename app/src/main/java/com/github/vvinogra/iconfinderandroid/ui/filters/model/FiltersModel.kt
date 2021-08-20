package com.github.vvinogra.iconfinderandroid.ui.filters.model

import com.github.vvinogra.iconfinderandroid.data.network.retrofit.IconFinderApi
import com.github.vvinogra.iconfinderandroid.ui.base.model.BaseModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FiltersModel @Inject constructor(
        private val api: IconFinderApi,
        private val filtersFactory: FiltersFactory
) : BaseModel() {
    companion object {
        private const val MAX_CATEGORIES = 15
        private const val MAX_STYLES = 15
    }

    fun getStylesFilterSection(): Single<FilterSection> {
        return api.listAllStyles(MAX_STYLES, null)
                .map {
                    filtersFactory.createStylesFilterSection(it)
                }
    }

    fun getCategoriesFilterSection(): Single<FilterSection> {
        return api.listAllCategories(MAX_CATEGORIES, null)
                .map {
                    filtersFactory.createCategoriesFilterSection(it)
                }
    }
}
