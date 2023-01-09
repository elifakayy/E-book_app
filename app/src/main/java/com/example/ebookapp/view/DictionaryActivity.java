package com.example.ebookapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.ebookapp.R;

public class DictionaryActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        webView = findViewById(R.id.webViewDic);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://sozluk.gov.tr/");


    }
}