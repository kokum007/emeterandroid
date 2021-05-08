package com.example.emeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class graphView2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_view2);


      String html = "<iframe width=\"450\" height=\"260\" style=\"border: 1px solid #cccccc;\" src=\"https://thingspeak.com/channels/1064781/charts/1?bgcolor=%230f2027&color=%23d62020&dynamic=true&results=60&type=spline\"> </iframe>";


//                WebView myWebView = (WebView) findViewById(R.id.webview);

        WebView webview;
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);


        webview.loadData(html, "text/html", null);
    }
}
