package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

import com.example.chap13.R;

public class ChronometerTest extends Activity {
	Chronometer mChrono;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choronometertest);
		
		mChrono = (Chronometer)findViewById(R.id.chrono);
	}
	
	public void onDestroy() {
		super.onDestroy();
		mChrono.stop();
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btnstart:
			mChrono.start();
			break;
		case R.id.btnstop:
			mChrono.stop();
			break;
		case R.id.btnreset:
			mChrono.setBase(SystemClock.elapsedRealtime());
			break;
		}
	}
}
