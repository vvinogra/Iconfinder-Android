package com.github.vvinogra.iconfinderandroid.ui.searchicons.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.vvinogra.iconfinderandroid.R
import com.github.vvinogra.iconfinderandroid.data.network.model.Icon
import com.github.vvinogra.iconfinderandroid.ui.searchicons.model.NetworkState
import com.squareup.picasso.Picasso
import com.github.vvinogra.iconfinderandroid.databinding.LiIconBinding
import com.github.vvinogra.iconfinderandroid.databinding.LiProgressBinding
import com.github.vvinogra.iconfinderandroid.ui.searchicons.presentation.IconPresentation
import com.github.vvinogra.iconfinderandroid.ui.utils.EndlessRecyclerViewScrollListener
import javax.inject.Inject

//class IconsListAdapter @Inject constructor(
//    private val picasso: Picasso
//) : PagedListAdapter<Icon, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
//
//    var networkState: NetworkState? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            R.layout.li_icon -> {
//                val iconBinding = LiIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//                IconsListViewHolder(iconBinding, picasso)
//            }
//            R.layout.li_progress -> {
//                val progressBinding = LiProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//
//                ProgressViewHolder(progressBinding)
//            }
//            else -> throw IllegalArgumentException("Unknown view type $viewType")
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return super.getItemCount() + if (hasExtraRow()) 1 else 0
//    }
//
//    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED
//
//    override fun getItemViewType(position: Int): Int {
//        return if (position == itemCount - 1 && hasExtraRow()) {
//            R.layout.li_progress
//        } else {
//            R.layout.li_icon
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (getItemViewType(position)) {
//            R.layout.li_icon -> getItem(position)?.let { (holder as IconsListViewHolder).bind(it) }
//            R.layout.li_progress -> (holder as ProgressViewHolder).bind(networkState)
//        }
//    }
//
//    class IconsListViewHolder(
//        private val iconBinding: LiIconBinding,
//        private val picasso: Picasso
//    ) : RecyclerView.ViewHolder(iconBinding.root) {
//        fun bind(item: Icon) {
//            picasso.load(item.rasterSizes.last().formats[0].previewUrl)
//                .fit()
//                .into(iconBinding.iconPreview)
//        }
//    }
//
//    class ProgressViewHolder(
//        private val progressBinding: LiProgressBinding
//    ) : RecyclerView.ViewHolder(progressBinding.root) {
//        fun bind(networkState: NetworkState?) {
//
//        }
//    }
//
//    companion object {
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Icon>() {
//            override fun areItemsTheSame(
//                oldConcert: Icon,
//                newConcert: Icon
//            ) = oldConcert.iconId == newConcert.iconId
//
//            override fun areContentsTheSame(
//                oldConcert: Icon,
//                newConcert: Icon
//            ) = oldConcert == newConcert
//        }
//    }
//}

class IconsListAdapter @Inject constructor(
    private val picasso: Picasso
) : RecyclerView.Adapter<IconsListAdapter.IconsListViewHolder>() {

    var loadMoreListener: ((Int) -> Unit)? = null

    private lateinit var scrollListener: EndlessRecyclerViewScrollListener

    private val icons = mutableListOf<IconPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val iconBinding = LiIconBinding.inflate(inflater, parent, false)

        return IconsListViewHolder(iconBinding, picasso)
    }

    override fun onBindViewHolder(holder: IconsListViewHolder, position: Int) {
        val iconItem = icons[position]

        holder.bind(iconItem)
    }

    override fun getItemCount(): Int {
        return icons.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        if (recyclerView.layoutManager is GridLayoutManager) {
            val gridLayoutManager = recyclerView.layoutManager as GridLayoutManager

            scrollListener = object : EndlessRecyclerViewScrollListener(gridLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    loadMoreListener?.invoke(page)
                }
            }
            recyclerView.addOnScrollListener(scrollListener)
        }
    }

    fun refresh() {
        icons.clear()
        scrollListener.resetState()
        notifyDataSetChanged()
    }

    fun updateData(newIcons: List<IconPresentation>) {
        icons.clear()

        icons.addAll(newIcons)

        notifyDataSetChanged()
    }

    class IconsListViewHolder(
        private val iconBinding: LiIconBinding,
        private val picasso: Picasso
    ) : RecyclerView.ViewHolder(iconBinding.root) {
        fun bind(item: IconPresentation) {
            picasso.load(item.previewUrl)
                .fit()
                .into(iconBinding.iconPreview)
        }
    }
}