package com.github.vvinogra.iconfinderandroid.ui.filters.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.vvinogra.iconfinderandroid.data.utils.IconFinderSchedulers
import com.github.vvinogra.iconfinderandroid.ui.base.viewmodel.BaseViewModel
import com.github.vvinogra.iconfinderandroid.ui.filters.model.FilterSection
import com.github.vvinogra.iconfinderandroid.ui.filters.model.FilterType
import com.github.vvinogra.iconfinderandroid.ui.filters.model.FiltersModel
import com.github.vvinogra.iconfinderandroid.ui.utils.MutableListLiveData
import javax.inject.Inject

//enum class FilterType1(val heading: String) {
//    Categories("Categories"),
//    Styles("Styles"),
//    Prices("Prices"),
//    Licences("Licences");
//
////    constructor(@StringRes val heading: Int)
//}
//
data class Filter(
        val filterType: FilterType,
        var filterState: FilterState = FilterState.Empty
)

class FiltersViewModel @Inject constructor(
        private val iconFinderSchedulers: IconFinderSchedulers,
        private val filtersModel: FiltersModel
) : BaseViewModel() {

//    private val _filtersState = MutableLiveData<FiltersState>()
//    val filtersState: LiveData<FiltersState> = _filtersState

    //    private val _pricesState = MutableLiveData<FiltersState>()
//    val pricesState: LiveData<FiltersState> = _pricesState
//
    private val _prices = MutableLiveData<FilterState>()
    val prices: LiveData<FilterState> = _prices

    private val _categories = MutableLiveData<FilterState>()
    val categories: LiveData<FilterState> = _categories

    private val _styles = MutableLiveData<FilterState>()
    val styles: LiveData<FilterState> = _styles

    private val _filters = MutableListLiveData<Filter>()
    val filters: LiveData<List<Filter>> = _filters
//    val filters: LiveData<List<Section>> = MediatorLiveData<List<Section>>().apply {
////        addSource(categories) {
////            if (it is FiltersState.Success) {
////                _filters
////            }
////        }
////
////        addSource(styles) {
////            if (it is FiltersState.Success) {
////
////            }
////        }
//    }

    companion object {
//        private val filters = listOf(FilterType1.Categories, FilterType1.Styles,
//                FilterType1.Prices, FilterType1.Licences)
    }

    init {
        _filters.addAll(listOf(
                Filter(FilterType.Categories),
                Filter(FilterType.Styles),
                Filter(FilterType.Prices),
                Filter(FilterType.Licences)
        ))

//        _filters.addAll(listOf(
//                FilterType.Categories,
//                FilterType.Styles,
//                FilterType.Prices,
//                FilterType.Licences))

        loadCategories()
        loadStyles()
    }

    private fun loadCategories() {
        compositeDisposable.add(filtersModel.getCategoriesFilterSection()
                .subscribeOn(iconFinderSchedulers.io())
                .observeOn(iconFinderSchedulers.main())
                .subscribe({
                    _filters.find {
                        it.filterType == FilterType.Categories
                    }?.let {
                        it.filterState = FilterState.Success()
                    }
                }, {

                }))
    }

    private fun loadStyles() {
        compositeDisposable.add(filtersModel.getStylesFilterSection()
                .subscribeOn(iconFinderSchedulers.io())
                .observeOn(iconFinderSchedulers.main())
                .subscribe({
//                    filtersState
                }, {

                }))
    }
}