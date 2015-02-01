package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.chap13.R;

public class ProgressTitle extends Activity {
	int mProg;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.progresstitle);

		mProg=5000;
		setProgress(mProg);
		setProgressBarVisibility(true);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.decfirst:
			if (mProg >= 200) mProg -= 200;
			setProgress(mProg);
			break;
		case R.id.incfirst:
			if (mProg <= 9800) mProg += 200;
			setProgress(mProg);
		}
	}	
}