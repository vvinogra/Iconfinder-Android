package com.github.vvinogra.iconfinderandroid.ui.searchicons.model

import com.github.vvinogra.iconfinderandroid.data.network.model.Icon
import com.github.vvinogra.iconfinderandroid.data.network.model.SearchIconsResponse
import com.github.vvinogra.iconfinderandroid.data.network.retrofit.IconFinderApi
import com.github.vvinogra.iconfinderandroid.ui.base.model.BaseModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchIconsModel @Inject constructor(
    private val api: IconFinderApi
) : BaseModel() {

    companion object {
        private const val PAGE_SIZE = 50
    }

    fun searchIcons(query: String): Single<SearchIconsResponse> {
        return searchIconsNextPage(query, 0)
                .flatMap {
                    if (it.icons.isEmpty()) {
                        Single.error(SearchIconsError.NotFoundError)
                    } else {
                        Single.just(it)
                    }
                }
    }

    fun searchIconsNextPage(query: String, pageNumber: Int): Single<SearchIconsResponse> {
        val offset = pageNumber * PAGE_SIZE
        val count = offset + PAGE_SIZE

        return api.searchIcons(query, offset, count)
    }

//    fun searchIcons(query: String): Listing<Icon> {
//        val config = PagedList.Config.Builder()
//            .setPageSize(PAGE_SIZE)
//            .build()
//
//        val factory = IconsDataSource.IconsDataSourceFactory(api, query)
//
//        val livePagedList = LivePagedListBuilder(factory, config)
//            .build()
//
//        return Listing(
//            pagedList = livePagedList,
//            networkState = switchMap(factory.source) { it.network },
//            refresh = { factory.source.value?.invalidate() },
//            refreshState = switchMap(factory.source) { it.initial })
//    }
//
}