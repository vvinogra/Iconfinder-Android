package com.github.vvinogra.iconfinderandroid.ui.filters.view

import android.view.LayoutInflater
import android.view.View
import androidx.core.view.iterator
import com.github.vvinogra.iconfinderandroid.R
import com.github.vvinogra.iconfinderandroid.databinding.LiFiltersItemBinding
import com.google.android.material.chip.Chip
import com.xwray.groupie.viewbinding.BindableItem
import com.xwray.groupie.viewbinding.GroupieViewHolder

class FiltersItem(private val filters: List<String>): BindableItem<LiFiltersItemBinding>() {

    override fun initializeViewBinding(view: View): LiFiltersItemBinding {
        return LiFiltersItemBinding.bind(view)
    }

    override fun createViewHolder(itemView: View): GroupieViewHolder<LiFiltersItemBinding> {
        val viewHolder: GroupieViewHolder<LiFiltersItemBinding> = super.createViewHolder(itemView)

        val chipGroup = viewHolder.binding.chipGroup

        val inflater = LayoutInflater.from(chipGroup.context)

        for (filter in filters) {
            val chip = inflater.inflate(R.layout.layout_item_chip_filter, chipGroup, false) as Chip

            chip.text = filter
            chipGroup.addView(chip)
        }

        return viewHolder
    }

    override fun bind(viewBinding: LiFiltersItemBinding, position: Int) {
//        for (item in viewBinding.chipGroup) {
//            item as Chip
//            item.text = filters.
//        }

//        viewBinding.chipGroup.addView(chip)
//        viewBinding.filterGroupTitleTv.text = "test"
//
//        viewBinding.root.setOnClickListener {
//            expandableGroup.onToggleExpanded()
//
//            viewBinding.expandIndicator.apply {
//                setImageResource(
//                        if (expandableGroup.isExpanded) R.drawable.ic_baseline_arrow_drop_up_24
//                        else R.drawable.ic_baseline_arrow_drop_down_24)
//            }
//        }
    }

    override fun getLayout(): Int {
        return R.layout.li_filters_item
    }
}

//class FiltersItem: BindableItem<LayoutItemChipFilterBinding>() {
//
//    override fun initializeViewBinding(view: View): LayoutItemChipFilterBinding {
//        return LayoutItemChipFilterBinding.bind(view)
//    }
//
//    override fun bind(viewBinding: LayoutItemChipFilterBinding, position: Int) {
//        viewBinding.root.text = "test chip"
//    }
//
//    override fun getLayout(): Int {
//        return R.layout.layout_item_chip_filter
//    }
//}