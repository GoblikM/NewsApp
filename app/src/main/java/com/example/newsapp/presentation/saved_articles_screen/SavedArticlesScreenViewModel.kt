package com.example.newsapp.presentation.saved_articles_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.repository.INewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedArticlesScreenViewModel @Inject
constructor(private val newsRepository: INewsRepository) : ViewModel() {

    var state by  mutableStateOf(SavedArticlesScreenState())

    init {
        getArticles()
    }

    private fun getArticles(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val articles = newsRepository.getAllArticlesFromDb().firstOrNull()
            state = state.copy(
                articles = articles?: emptyList(),
                isLoading = false
            )
        }

    }

}