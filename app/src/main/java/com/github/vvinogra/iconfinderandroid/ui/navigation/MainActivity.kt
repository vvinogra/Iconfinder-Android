package com.github.vvinogra.iconfinderandroid.ui.navigation

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.github.vvinogra.iconfinderandroid.R
import com.github.vvinogra.iconfinderandroid.databinding.MainActivityBinding
import com.github.vvinogra.iconfinderandroid.ui.base.view.BaseActivity
import com.github.vvinogra.iconfinderandroid.ui.utils.visibleIf

class MainActivity : BaseActivity<MainActivityBinding>(MainActivityBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isHidden = destination.id == R.id.filters_fragment

            binding.bottomNavigationView.visibleIf(!isHidden)
        }

        binding.bottomNavigationView.run {
            setupWithNavController(navController)
        }
    }
}