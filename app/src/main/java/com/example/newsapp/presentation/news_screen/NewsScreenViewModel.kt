package com.example.newsapp.presentation.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.repository.INewsRepository
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: INewsRepository
) : ViewModel() {

    /**
     * Mutable state of [NewsScreenState]
     * This is the state that will be observed by the UI
     * and will be updated by the ViewModel
     */
    var state by mutableStateOf(NewsScreenState())

    init {
       getNewsArticles()
    }

    private fun getNewsArticles() {
        viewModelScope.launch {
            state = when(val result = newsRepository.getNews()){
                is Resource.Success -> {
                    state.copy(
                        articles =  result.data ?: emptyList(),
                        isLoading = false,
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        error = result.message,
                        isLoading = false,
                        articles = emptyList()
                    )
                }
            }


        }
    }

}