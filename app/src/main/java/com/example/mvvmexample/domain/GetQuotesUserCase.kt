package com.example.mvvmexample.domain

import com.example.mvvmexample.data.QuoteRepository
import com.example.mvvmexample.data.model.QuoteModel
import javax.inject.Inject

class GetQuotesUserCase @Inject constructor(private val repository : QuoteRepository){

    suspend operator fun invoke() = repository.getAllQuotes()
}