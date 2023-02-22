package com.example.giphyapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giphyapp.R
import com.example.giphyapp.adapters.GiphyAdapter
import com.example.giphyapp.repository.GiphyRepository
import com.example.giphyapp.util.Resource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.paginationProgressBar

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: GiphyViewModel
    lateinit var giphyAdapter: GiphyAdapter
    private val TAG = "GiphyActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val giphyRepository = GiphyRepository()
        val viewModelProviderFactory = GiphyViewModelProviderFactory(giphyRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(GiphyViewModel::class.java)

        viewModel.giphy.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { giphyResponse ->
                        giphyAdapter.differ.submitList(giphyResponse.data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        giphyAdapter = GiphyAdapter()
        rvGiphy.apply {
            adapter = giphyAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }
}