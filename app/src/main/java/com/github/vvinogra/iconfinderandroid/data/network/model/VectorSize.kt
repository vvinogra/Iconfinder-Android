package com.github.vvinogra.iconfinderandroid.data.network.model

import com.google.gson.annotations.SerializedName

data class VectorSize(
    @SerializedName("formats")
    val formats: List<Format>,
    @SerializedName("size")
    val size: Int,
    @SerializedName("size_height")
    val sizeHeight: Int,
    @SerializedName("size_width")
    val sizeWidth: Int,
    @SerializedName("target_sizes")
    val targetSizes: List<List<Int>>
)