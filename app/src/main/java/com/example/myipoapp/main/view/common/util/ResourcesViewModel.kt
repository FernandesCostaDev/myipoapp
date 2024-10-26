package com.example.myipoapp.main.view.common.util

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResourcesViewModel : ViewModel(){
    val cbActivatedData = MutableLiveData<Array<String>>()
}