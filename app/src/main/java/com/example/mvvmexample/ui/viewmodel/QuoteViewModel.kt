package com.example.mvvmexample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.data.model.QuoteModel
import com.example.mvvmexample.data.model.QuoteProvider
import com.example.mvvmexample.domain.GetQuotesUserCase
import com.example.mvvmexample.domain.GetRandomQuoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuoteUseCase : GetQuotesUserCase,
    private val getRandomQuoteUseCase : GetRandomQuoteUseCase

):ViewModel() {

    val quoteModel = MutableLiveData<QuoteModel>()
    var isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            val result = getQuoteUseCase()
            isLoading.postValue(true)
            if (!result.isNullOrEmpty()) {
                quoteModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }


    fun randomQuote() {
        isLoading.postValue(true)
        val quote = getRandomQuoteUseCase()
        if (quote != null) {
            quoteModel.postValue(quote)
        }
        isLoading.postValue(false)
    }
}