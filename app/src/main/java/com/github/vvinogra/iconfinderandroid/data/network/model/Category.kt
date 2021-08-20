package com.github.vvinogra.iconfinderandroid.data.network.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("name")
    val name: String
)