package com.github.vvinogra.iconfinderandroid.data.network.model

import com.google.gson.annotations.SerializedName

data class Format(
    @SerializedName("download_url")
    val downloadUrl: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("preview_url")
    val previewUrl: String = ""
)