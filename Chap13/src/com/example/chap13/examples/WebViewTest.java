package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.chap13.R;

public class WebViewTest extends Activity {
	WebView mWeb;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webviewtest);

		mWeb = (WebView)findViewById(R.id.web);
		mWeb.setWebViewClient(new MyWebClient());
		WebSettings set = mWeb.getSettings();
		set.setJavaScriptEnabled(true);
		set.setBuiltInZoomControls(true);
		mWeb.loadUrl("about:blank");
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btngo:
			String url;
			EditText addr = (EditText)findViewById(R.id.address);
			url = addr.getText().toString();
			mWeb.loadUrl(url);
			break;
		case R.id.btnback:
			if (mWeb.canGoBack()) {
				mWeb.goBack();
			}
			break;
		case R.id.btnforward:
			if (mWeb.canGoForward()) {
				mWeb.goForward();
			}
			break;
		case R.id.btnlocal:
			mWeb.loadUrl("file:///android_asset/test.html");
			break;
		}
	}
	
	class MyWebClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}