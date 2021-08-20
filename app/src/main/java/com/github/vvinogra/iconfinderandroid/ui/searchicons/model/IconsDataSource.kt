package com.github.vvinogra.iconfinderandroid.ui.searchicons.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.github.vvinogra.iconfinderandroid.data.network.model.Icon
import com.github.vvinogra.iconfinderandroid.data.network.retrofit.IconFinderApi

class IconsDataSource constructor(
        private val api: IconFinderApi,
        private val searchQuery: String
) : PageKeyedDataSource<Int, Icon>() {

    val initial = MutableLiveData<NetworkState>()
    val network = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Icon>) {
        // Fetch data synchronously.
        // Load an initial data set so the paged list is not empty.
        val currentOffset = 0

        api.searchIcons(searchQuery, currentOffset, params.requestedLoadSize)
            .doOnSubscribe { postInitialState(NetworkState.LOADING) }
            .blockingSubscribe({
                val nextOffset = currentOffset + it.icons.size

                callback.onResult(it.icons, null, nextOffset)

                postInitialState(NetworkState.LOADED)
            }, {
                postInitialState(NetworkState.error(it))
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Icon>) {
        val currentOffset = params.key

        api.searchIcons(searchQuery, currentOffset, params.requestedLoadSize)
            .doOnSubscribe { postAfterState(NetworkState.LOADING) }
            .subscribe({
                val nextOffset = currentOffset + it.icons.size

                callback.onResult(it.icons, nextOffset)

                postAfterState(NetworkState.LOADED)
            }, {
                postAfterState(NetworkState.error(it))
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Icon>) {
        // ignored, since we only ever append to our initial load
    }

    private fun postInitialState(networkState: NetworkState) {
        initial.postValue(networkState)
        network.postValue(networkState)
    }

    private fun postAfterState(networkState: NetworkState) {
        network.postValue(networkState)
    }

    class IconsDataSourceFactory(
        private val api: IconFinderApi,
        private val searchQuery: String
    ) : DataSource.Factory<Int, Icon>() {

        val source = MutableLiveData<IconsDataSource>()

        override fun create(): DataSource<Int, Icon> {
            val sourceData = IconsDataSource(api, searchQuery)

            source.postValue(sourceData)

            return sourceData
        }
    }
}