package com.example.newsapp.presentation.article_detail_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.repository.INewsRepository
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(
    private val newsRepository: INewsRepository, savedStateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(ArticleDetailScreenState())

    // Get the article uri from the savedStateHandle and remove the {uri} from the string
    private val articleURI : String = checkNotNull(savedStateHandle.get<String>("uri")
        ?.replace("{uri}",""))

    /**
     * This init block will call the getArticleDetails function
     */
    init {
        Log.d("articleUri", "ArticleDetailViewModel: $articleURI")
        getArticleDetails(articleURI = articleURI)
    }

    /**
     * This function will fetch the article details from the API and update the state
     * @param articleURI
     *
     */
    private fun getArticleDetails(articleURI: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            state = when(val result = newsRepository.getArticleDetails(articleURI)){
                is Resource.Success -> {
                    state.copy(
                        article =  result.data,
                        isLoading = false,
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        error = result.message,
                        isLoading = false,
                    )
                }
            }
            Log.d("articleState", "getArticleDetails: $state")
        }

    }

}

