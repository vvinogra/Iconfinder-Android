package com.github.vvinogra.iconfinderandroid.ui.filters.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.vvinogra.iconfinderandroid.databinding.FiltersFragmentBinding
import com.github.vvinogra.iconfinderandroid.di.viewmodel.DaggerViewModelFactory
import com.github.vvinogra.iconfinderandroid.ui.base.view.BaseFragment
import com.github.vvinogra.iconfinderandroid.ui.filters.viewmodel.FiltersViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.Section
import javax.inject.Inject

class FiltersFragment : BaseFragment<FiltersFragmentBinding>(FiltersFragmentBinding::inflate) {

    @Inject lateinit var daggerViewModelFactory: DaggerViewModelFactory

    private val filtersViewModel: FiltersViewModel by viewModels { daggerViewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(binding.toolbar.toolbarActionbar)

        val groupAdapter = GroupieAdapter()
//        groupAdapter.getGroup()
//        groupAdapter.add(Section(FiltersHeadingItem("Prices")).apply {
//            add(FiltersItem(mutableListOf("All prices", "Free", "Premium")))
//        })
//        groupAdapter.add(Section(FiltersHeadingItem("Styles")).apply {
//            add(FiltersItem(mutableListOf("All styles", "Solid", "Outline", "Flat", "Filled Outline")))
//        })

        Section(FiltersHeaderItem("Styles")).

        binding.filtersRecyclerView.run {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
        }

        filtersViewModel.filters.observe(viewLifecycleOwner, {
//            val test = it.map { element ->
//                Section(FiltersHeadingItem(element.heading))
//            }
            groupAdapter.addAll(it)
        })
    }

    private fun setupToolbar(toolbar: Toolbar) {
        val navController = findNavController()
        toolbar.setupWithNavController(navController, AppBarConfiguration(navController.graph))
    }
}