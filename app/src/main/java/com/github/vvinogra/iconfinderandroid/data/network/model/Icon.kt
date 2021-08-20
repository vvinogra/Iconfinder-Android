package com.github.vvinogra.iconfinderandroid.data.network.model

import com.google.gson.annotations.SerializedName

data class Icon(
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("containers")
        val containers: List<Format>,
        @SerializedName("icon_id")
        val iconId: Int,
        @SerializedName("is_icon_glyph")
        val isIconGlyph: Boolean,
        @SerializedName("is_premium")
        val isPremium: Boolean,
        @SerializedName("prices")
        val prices: List<Price>,
        @SerializedName("published_at")
        val publishedAt: String,
        @SerializedName("raster_sizes")
        val rasterSizes: List<RasterSize>,
        @SerializedName("styles")
        val styles: List<Style>,
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("type")
        val type: String,
        @SerializedName("vector_sizes")
        val vectorSizes: List<VectorSize>
)