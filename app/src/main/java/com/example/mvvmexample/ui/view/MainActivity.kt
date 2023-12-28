package com.example.mvvmexample.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.mvvmexample.databinding.ActivityMainBinding
import com.example.mvvmexample.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quoteViewModel: QuoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()

        quoteViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })
        quoteViewModel.quoteModel.observe(this, Observer { currentQuote ->
            binding.tvAuthor.text = currentQuote.author
            binding.tvQuote.text = currentQuote.quote
        })

        binding.viewContainer.setOnClickListener { quoteViewModel.randomQuote() }
    }


}