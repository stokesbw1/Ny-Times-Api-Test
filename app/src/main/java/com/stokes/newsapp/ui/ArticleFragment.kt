package com.stokes.newsapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.stokes.newsapp.R
import com.stokes.newsapp.data.PopularNewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    val viewModel: PopularNewsViewModel by viewModels()
    private lateinit var webView: WebView

    private val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.clickedArticle
        webView = view.findViewById(R.id.webView)
        webView.apply {
            webViewClient = WebViewClient()
            article?.url?.let { loadUrl(it) }
        }
    }

}