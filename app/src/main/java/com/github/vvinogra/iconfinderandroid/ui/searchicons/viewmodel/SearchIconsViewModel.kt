package com.github.vvinogra.iconfinderandroid.ui.searchicons.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.vvinogra.iconfinderandroid.data.network.model.SearchIconsResponse
import com.github.vvinogra.iconfinderandroid.data.utils.IconFinderSchedulers
import com.github.vvinogra.iconfinderandroid.ui.base.viewmodel.BaseViewModel
import com.github.vvinogra.iconfinderandroid.ui.searchicons.model.SearchIconsModel
import com.github.vvinogra.iconfinderandroid.ui.searchicons.presentation.SearchIconsPresentationFactory
import javax.inject.Inject


class SearchIconsViewModel @Inject constructor(
        private val searchIconsModel: SearchIconsModel,
        private val iconFinderSchedulers: IconFinderSchedulers,
        private val searchIconsPresentationFactory: SearchIconsPresentationFactory
) : BaseViewModel() {

    var queryText: String = ""
    private set

    private val _iconListState = MutableLiveData<IconsListState>()
    val iconListState: LiveData<IconsListState> = _iconListState

    init {
        loadInitialSearchResults()
    }

    fun showSearchResults(searchQuery: String): Boolean {
        queryText = searchQuery

        loadInitialSearchResults()
        return true
    }

    fun refresh() {
        loadInitialSearchResults()
    }

    fun loadMoreSearchResults(page: Int) {
        compositeDisposable.add(searchIconsModel.searchIconsNextPage(queryText, page)
                .subscribeOn(iconFinderSchedulers.io())
                .observeOn(iconFinderSchedulers.main())
                .subscribe({
                    setIconListSuccessState(it)
                }, {

                })
        )
    }

    private fun loadInitialSearchResults() {
        compositeDisposable.add(searchIconsModel.searchIcons(queryText)
                .subscribeOn(iconFinderSchedulers.io())
                .observeOn(iconFinderSchedulers.main())
                .doOnSubscribe {
                    _iconListState.value = IconsListState.Loading
                }
                .subscribe({
                    setIconListSuccessState(it)
                }, {
                    _iconListState.value = IconsListState.Failure(it)
                })
        )
    }

    private fun setIconListSuccessState(searchIconsResponse: SearchIconsResponse) {
        val presentation = searchIconsPresentationFactory.createSearchIconsPresentation(searchIconsResponse)

        // Append items for pagination
        val presentationWithItems = (_iconListState.value as? IconsListState.Success)?.let {
            presentation.copy(icons = it.searchIconsPresentation.icons + presentation.icons)
        } ?: presentation

        _iconListState.value = IconsListState.Success(presentationWithItems)
    }
}