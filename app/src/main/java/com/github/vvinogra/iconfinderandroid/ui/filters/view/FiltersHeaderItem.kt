package com.github.vvinogra.iconfinderandroid.ui.filters.view

import android.view.View
import com.github.vvinogra.iconfinderandroid.R
import com.github.vvinogra.iconfinderandroid.databinding.LiFiltersHeadingItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class FiltersHeaderItem(private val heading: String): BindableItem<LiFiltersHeadingItemBinding>() {
    override fun initializeViewBinding(view: View): LiFiltersHeadingItemBinding {
        return LiFiltersHeadingItemBinding.bind(view)
    }

    override fun bind(viewBinding: LiFiltersHeadingItemBinding, position: Int) {
        viewBinding.filterGroupTitleTv.text = heading
    }

    override fun getLayout(): Int {
        return R.layout.li_filters_heading_item
    }
}