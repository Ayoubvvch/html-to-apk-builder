package com.vedicmath.app

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

/**
 * النشاط الرئيسي للتطبيق - يعرض محتوى HTML باستخدام WebView
 */
class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private var progressDialog: ProgressDialog? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // تهيئة WebView
        webView = findViewById(R.id.webView)

        // إعدادات WebView
        val webSettings: WebSettings = webView.settings

        // تفعيل JavaScript للتفاعل مع الصفحة
        webSettings.javaScriptEnabled = true

        // تفعيل تخزين DOM
        webSettings.domStorageEnabled = true

        // تفعيل التكبير والتصغير
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false

        // إعدادات العرض
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true

        // تفعيل تحميل الصور تلقائياً
        webSettings.loadsImagesAutomatically = true

        // إعدادات الأمان
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true

        // تعيين عميل WebView مخصص
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url.toString()

                // إذا كان الرابط خارجي ( يبدأ بـ http أو https )، افتحه في المتصفح الخارجي
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }

                // الروابط المحلية تُعرض داخل WebView
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // إخفاء مؤشر التحميل عند اكتمال تحميل الصفحة
                progressDialog?.dismiss()
            }
        }

        // تعيين عميل Chrome لتحميل الملفات بشكل أفضل
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

        // تحميل ملف HTML من مجلد assets
        webView.loadUrl("file:///android_asset/index.html")
    }

    /**
     * معالجة زر الرجوع في Android
     */
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // إذا كان بالإمكان الرجوع في تاريخ WebView، افعل ذلك
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            // أغلق التطبيق
            super.onBackPressed()
        }
    }

    /**
     * حفظ حالة WebView عند تدوير الشاشة
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    /**
     * استعادة حالة WebView بعد تدوير الشاشة
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }
}
