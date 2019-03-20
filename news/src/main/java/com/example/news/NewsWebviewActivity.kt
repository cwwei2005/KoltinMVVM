package com.example.news

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_test_webview.*

@Route(path = "/news/NewsWebviewActivity")
class NewsWebviewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_webview)

        webview.loadUrl(intent.getStringExtra("url"))
    }
}
