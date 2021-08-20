package com.github.vvinogra.iconfinderandroid.data.network.model

import com.google.gson.annotations.SerializedName

data class SearchIconsResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("icons")
    val icons: List<Icon>
)