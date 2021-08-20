package com.github.vvinogra.iconfinderandroid.ui.searchicons.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.vvinogra.iconfinderandroid.R
import com.github.vvinogra.iconfinderandroid.databinding.NoIconsFoundViewBinding

class NoIconsFoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: NoIconsFoundViewBinding = NoIconsFoundViewBinding.inflate(
        LayoutInflater.from(context), this, true)

    fun updateTitleMessage(searchQuery: String) {
        binding.noIconsFoundViewTitle.text =
            resources.getString(R.string.no_icons_found_view_title, searchQuery)
    }
}