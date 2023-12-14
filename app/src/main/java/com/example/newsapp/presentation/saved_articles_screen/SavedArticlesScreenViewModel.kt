package com.example.newsapp.presentation.saved_articles_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.repository.INewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedArticlesScreenViewModel @Inject
constructor(newRepository: INewsRepository) : ViewModel() {

    val state = mutableStateOf(SavedArticlesScreenState())
}