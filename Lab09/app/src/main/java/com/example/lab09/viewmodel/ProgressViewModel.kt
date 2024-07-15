package com.example.lab09.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProgressViewModel : ViewModel() {
    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> get() = _progress

    fun setProgress(value: Int) {
        _progress.value = value
    }
}
