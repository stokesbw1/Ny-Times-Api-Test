package com.stokes.newsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stokes.newsapp.R
import com.stokes.newsapp.adapters.PopularNewsAdapter
import com.stokes.newsapp.data.PopularNewsViewModel
import com.stokes.newsapp.util.Constants.CLICKED_ARTICLE
import com.stokes.newsapp.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularNewsFragment : Fragment(R.layout.fragment_popular_news) {

    private val viewModel: PopularNewsViewModel by viewModels()
       lateinit var popularNewsAdapter: PopularNewsAdapter
       lateinit var recyclerView: RecyclerView
       lateinit var progressBar: ProgressBar
       private val TAG = "PopularNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = (activity as MainActivity).viewModel
        recyclerView = view.findViewById(R.id.rvPopularNews)
        progressBar = view.findViewById(R.id.paginationProgressBar)
        setUpRecyclerView()

        popularNewsAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable(CLICKED_ARTICLE, it)
            }
            findNavController().navigate(
                R.id.action_popularNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.getPopularNewsData().observe(viewLifecycleOwner, Observer { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    response.data?.let { popularNewsResponse ->
                        popularNewsAdapter.differ.submitList(popularNewsResponse.results)
                    }
                }

                Status.ERROR -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.i(TAG, "An error occurred: $message")
                    }
                }

                Status.LOADING -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        popularNewsAdapter = PopularNewsAdapter()
        recyclerView.apply {
            adapter = popularNewsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}