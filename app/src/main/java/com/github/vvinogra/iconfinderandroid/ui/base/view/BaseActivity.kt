package com.github.vvinogra.iconfinderandroid.ui.base.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import dagger.android.AndroidInjection

abstract class BaseActivity<T : ViewBinding>(
    private val initBinding: (LayoutInflater) -> T
) : AppCompatActivity() {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        binding = initBinding.invoke(layoutInflater)

        setContentView(binding.root)
    }
}