package com.glogachev.developerslife.ui.pages.hot

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.glogachev.developerslife.domain.NetworkResult
import com.glogachev.developerslife.domain.Repository
import com.glogachev.developerslife.domain.model.LatesHotTopPost
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HotViewModel(
    private val repository: Repository
) : ViewModel() {
    private var _result = MutableLiveData<NetworkResult<List<LatesHotTopPost>>>()
    val result get() = _result

    fun obtainHotPost() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getHotPost()
            withContext(Dispatchers.Main) {
                _result.value = result

            }
        }
    }
}

class HotViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HotViewModel(repository = repository) as T
    }
}