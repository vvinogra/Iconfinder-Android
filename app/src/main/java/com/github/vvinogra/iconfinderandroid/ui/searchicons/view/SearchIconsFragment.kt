package com.github.vvinogra.iconfinderandroid.ui.searchicons.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.vvinogra.iconfinderandroid.R
import com.github.vvinogra.iconfinderandroid.databinding.SearchIconsFragmentBinding
import com.github.vvinogra.iconfinderandroid.di.viewmodel.DaggerViewModelFactory
import com.github.vvinogra.iconfinderandroid.ui.base.view.BaseFragment
import com.github.vvinogra.iconfinderandroid.ui.searchicons.model.SearchIconsError
import com.github.vvinogra.iconfinderandroid.ui.searchicons.model.Status
import com.github.vvinogra.iconfinderandroid.ui.searchicons.presentation.SearchIconsPresentationFactory
import com.github.vvinogra.iconfinderandroid.ui.searchicons.viewmodel.IconsListState
import com.github.vvinogra.iconfinderandroid.ui.searchicons.viewmodel.SearchIconsViewModel
import com.github.vvinogra.iconfinderandroid.ui.utils.GridAutoFitLayoutManager
import com.github.vvinogra.iconfinderandroid.ui.utils.gone
import com.github.vvinogra.iconfinderandroid.ui.utils.visible
import com.github.vvinogra.iconfinderandroid.ui.utils.visibleIf
import javax.inject.Inject

class SearchIconsFragment : BaseFragment<SearchIconsFragmentBinding>(SearchIconsFragmentBinding::inflate) {

    @Inject lateinit var iconsListAdapter: IconsListAdapter
    @Inject lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private val searchIconsViewModel: SearchIconsViewModel by viewModels { daggerViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(binding.toolbar.toolbarActionbar)

        binding.iconsRecyclerView.run {
            layoutManager = GridAutoFitLayoutManager(
                    context, resources.getDimension(R.dimen.search_icons_item_size).toInt())

            adapter = iconsListAdapter.also {
                it.loadMoreListener = { page ->
                    searchIconsViewModel.loadMoreSearchResults(page)
                }
            }
        }

        binding.swipeRefreshIconList.setOnRefreshListener {
            searchIconsViewModel.refresh()
        }

        subscribeEvents()
    }

    private fun setupToolbar(toolbar: Toolbar) {
        val navController = findNavController()
        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))

        toolbar.inflateMenu(R.menu.search_icons)

        val searchMenuItem = toolbar.menu.findItem(R.id.action_search)

        val searchView = searchMenuItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchIconsViewModel.showSearchResults(query)

                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }

                searchMenuItem.collapseActionView()

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun subscribeEvents() {
//        searchIconsViewModel.iconList.observe(viewLifecycleOwner) {
//            iconsListAdapter.updateData(it)
//        }
        searchIconsViewModel.iconListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is IconsListState.Loading -> {
                    iconsListAdapter.refresh()
                }
                is IconsListState.Success -> {
                    state.searchIconsPresentation.let {
                        iconsListAdapter.updateData(it.icons)
                        binding.resultsFiltersComponent.applyPresentation(it.resultsFiltersPresentation)
                    }
                }
                is IconsListState.Failure -> {
                    when (state.failure) {
                        is SearchIconsError.NotFoundError -> {
                            binding.noIconsFoundView.updateTitleMessage(searchIconsViewModel.queryText)
                        }
                    }
                }
            }

            binding.run {
                noIconsFoundView.visibleIf(state is IconsListState.Failure &&
                        state.failure is SearchIconsError.NotFoundError)
                resultsFiltersComponent.visibleIf(state is IconsListState.Success)
                iconsLinearLayout.visibleIf(state is IconsListState.Success)

                swipeRefreshIconList.isRefreshing = state is IconsListState.Loading
            }
        }

//        searchIconsViewModel.refreshState.observe(viewLifecycleOwner) {
//            when (it.status) {
//                Status.RUNNING -> {
//                    iconsListAdapter.refresh()
//                }
//                Status.SUCCESS -> {
//                    binding.noIconsFoundView.gone()
//                    binding.resultsFiltersComponent.applyPresentation()
//                    binding.iconsLinearLayout.visible()
//                }
//                Status.FAILED -> {
//                    when (it.error) {
//                        is SearchIconsError.NotFoundError -> {
//                            binding.run {
//                                noIconsFoundView.updateTitleMessage(searchIconsViewModel.queryText)
//
//                                noIconsFoundView.visible()
//                                iconsLinearLayout.gone()
//                            }
//                        }
//                    }
//                }
//            }
//
//            binding.swipeRefreshIconList.isRefreshing = it.status == Status.RUNNING
//        }
    }
}