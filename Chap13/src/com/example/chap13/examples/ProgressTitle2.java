package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.chap13.R;

public class ProgressTitle2 extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.progresstitle2);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.start:
			setProgressBarIndeterminateVisibility(true);
			break;
		case R.id.stop:
			setProgressBarIndeterminateVisibility(false);
			break;
		}
	}
}