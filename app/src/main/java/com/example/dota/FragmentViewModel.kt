package com.example.dota

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class FragmentViewModel : ViewModel() {
    val liveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}