package com.github.vvinogra.iconfinderandroid.ui.searchicons.viewmodel

import com.github.vvinogra.iconfinderandroid.ui.searchicons.presentation.SearchIconsPresentation

sealed class IconsListState {
    object Loading : IconsListState()
    data class Success(val searchIconsPresentation: SearchIconsPresentation) : IconsListState()
    data class Failure(val failure: Throwable) : IconsListState()
}