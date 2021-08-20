package com.github.vvinogra.iconfinderandroid.ui.searchicons.model

import java.lang.Error

sealed class SearchIconsError: Error() {
    object NotFoundError : SearchIconsError()
}