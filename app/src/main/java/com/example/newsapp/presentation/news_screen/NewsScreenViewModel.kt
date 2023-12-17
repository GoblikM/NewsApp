package com.example.newsapp.presentation.news_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.newsResponse.Result
import com.example.newsapp.domain.repository.INewsRepository
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
    var page by mutableIntStateOf(1)

    init {
       getNewsArticles()
    }

    fun onEvent(event: NewsScreenEvent) {
        when (event) {
            is NewsScreenEvent.OnSaveArticleClicked -> {
                viewModelScope.launch {
                    saveArticle(event.article)
                }
            }
            is NewsScreenEvent.onRefresh -> {
                viewModelScope.launch {
                    state.copy(isRefreshing = true)
                    delay(1000)
                    page += 1
                    if(page > 10){
                        page = 1
                    }
                    getNewsArticles(page)
                }

            }
        }
    }

    private fun getNewsArticles(page: Int = 1) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            state = when(val result = newsRepository.getNews(page)){
                is Resource.Success -> {
                    state.copy(
                        articles =  result.data ?: emptyList(),
                        isLoading = false,
                        isRefreshing = false
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        error = result.message,
                        isLoading = false,
                        isRefreshing = false,
                        articles = emptyList()
                    )
                }
            }


        }
    }

    private suspend fun saveArticle(article : Result){
            try {
                Log.d("Article", "saveArticle: $article")
                newsRepository.insertArticleToDb(article)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }



}