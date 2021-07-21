package com.stokes.newsapp.ui

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.stokes.newsapp.R
import com.stokes.newsapp.data.PopularNewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArticleFragment : Fragment(R.layout.fragment_article) {

    val viewModel: PopularNewsViewModel by viewModels()
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar

    private val args: ArticleFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled", "CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.clickedArticle



        progressBar = view.findViewById(R.id.webViewProgressBar)
            webView = view.findViewById(R.id.webView)



        webView.settings.javaScriptEnabled = true;
        webView.settings.loadWithOverviewMode = true;
        webView.settings.useWideViewPort = true;
        webView.webViewClient = object : WebViewClient(){

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                // Enable disable back forward button
                progressBar.visibility = View.INVISIBLE
            }
        }

        webView.apply {
            webViewClient = WebViewClient()
            article?.url?.let {
                Log.i("ArticleFragment", "ArticleFragment ArticleFragment: " + article.url)
                loadUrl(it) }
        }

    }

}