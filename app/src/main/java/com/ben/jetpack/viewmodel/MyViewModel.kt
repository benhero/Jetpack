package com.ben.jetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * com.ben.jetpack.viewmodel
 *
 * @author Benhero
 * @date   2019/10/29
 */
class MyViewModel : ViewModel() {
    val num = MutableLiveData(0)
}