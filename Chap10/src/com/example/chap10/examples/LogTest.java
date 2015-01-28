package com.example.chap10.examples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.example.chap10.*;

public class LogTest extends ActionBarActivity {
	private static final String TAG = "LogTest";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logtest);
		
		Log.v(TAG, "onCreate");
	}

	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy");
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			Log.v(TAG, "First Button Pressed");
			break;
		case R.id.btn2:
			Log.v(TAG, "Second Button Pressed");
			break;
		}
	}
}
	
