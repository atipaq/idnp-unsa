package com.example.lab09

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

class MyApplication : Application(), ViewModelStoreOwner {
    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }
    private val factory by lazy {
        ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }

    override val viewModelStore: ViewModelStore
        get() = appViewModelStore

    fun getViewModelFactory(): ViewModelProvider.Factory = factory
}