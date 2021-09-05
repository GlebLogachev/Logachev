package com.glogachev.developerslife.ui.pages.random

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.glogachev.developerslife.domain.NetworkResult
import com.glogachev.developerslife.domain.Repository
import com.glogachev.developerslife.domain.model.RandomPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RandomPostViewModel(
    private val repository: Repository
) : ViewModel() {
    private var _result = MutableLiveData<NetworkResult<RandomPost>>()
    val result get() = _result

    fun obtainRandomPost() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getRandomPost()
            withContext(Dispatchers.Main) {
                _result.value = result

            }
        }
    }
}

class RandomViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RandomPostViewModel(repository = repository) as T
    }
}