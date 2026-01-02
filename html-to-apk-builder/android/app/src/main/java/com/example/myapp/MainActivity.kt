package com.example.myapp

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var progressDialog: ProgressDialog? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)

        val webSettings: WebSettings = webView.settings

        // Enable JavaScript for interactive content
        webSettings.javaScriptEnabled = true
        
        // Enable DOM storage for localStorage/sessionStorage
        webSettings.domStorageEnabled = true
        
        // Optimize viewport settings
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        
        // Enable/disable zoom based on requirement
        webSettings.builtInZoomControls = false
        webSettings.displayZoomControls = false
        
        // Performance settings
        webSettings.loadsImagesAutomatically = true
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT
        
        // Security settings
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true
        webSettings.allowFileAccessFromFileURLs = true
        webSettings.allowUniversalAccessFromFileURLs = true
        
        // Disable geolocation
        webSettings.setGeolocationEnabled(false)
        
        // Prevent mixed content issues
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW

        // WebView client to handle navigation
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()
                
                // Open external links in browser
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }
                
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressDialog?.dismiss()
            }
        }

        // Chrome client for progress bar
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    if (progressDialog == null) {
                        progressDialog = ProgressDialog(this@MainActivity).apply {
                            setMessage(getString(R.string.loading))
                            setCancelable(false)
                            show()
                        }
                    }
                    progressDialog?.progress = newProgress
                } else {
                    progressDialog?.dismiss()
                    progressDialog = null
                }
            }
        }

        // Load the HTML file from assets
        webView.loadUrl("file:///android_asset/www/index.html")
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }
}
