package com.glogachev.developerslife.ui.pages.latest

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

class LatestViewModel(
    private val repository: Repository
) : ViewModel() {
    private var _result = MutableLiveData<NetworkResult<List<LatesHotTopPost>>>()
    val result get() = _result

    fun obtainLatestPost() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getLatestPost()
            withContext(Dispatchers.Main) {
                _result.value = result

            }
        }
    }
}

class LatestViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LatestViewModel(repository = repository) as T
    }
}