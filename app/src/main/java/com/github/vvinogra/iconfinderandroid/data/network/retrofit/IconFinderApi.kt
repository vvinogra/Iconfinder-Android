package com.github.vvinogra.iconfinderandroid.data.network.retrofit

import com.github.vvinogra.iconfinderandroid.data.network.Constants
import com.github.vvinogra.iconfinderandroid.data.network.model.Category
import com.github.vvinogra.iconfinderandroid.data.network.model.SearchIconsResponse
import com.github.vvinogra.iconfinderandroid.data.network.model.Style
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IconFinderApi {
    @GET(Constants.Endpoint.SEARCH_ICONS)
    fun searchIcons(
            @Query(Constants.SEARCH_ICONS_PARAMS.QUERY) query: String,
            @Query(Constants.SEARCH_ICONS_PARAMS.OFFSET) offset: Int,
            @Query(Constants.SEARCH_ICONS_PARAMS.COUNT) count: Int
    ): Single<SearchIconsResponse>

    @GET(Constants.Endpoint.LIST_STYLES)
    fun listAllStyles(
            @Query(Constants.LIST_STYLES_PARAMS.COUNT) count: Int,
            @Query(Constants.LIST_STYLES_PARAMS.AFTER) after: String?
    ): Single<List<Style>>

    @GET(Constants.Endpoint.LIST_CATEGORIES)
    fun listAllCategories(
            @Query(Constants.LIST_CATEGORIES_PARAMS.COUNT) count: Int,
            @Query(Constants.LIST_CATEGORIES_PARAMS.AFTER) after: String?
    ): Single<List<Category>>
}