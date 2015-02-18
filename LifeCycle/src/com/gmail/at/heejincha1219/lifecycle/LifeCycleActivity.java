package com.gmail.at.heejincha1219.lifecycle;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class LifeCycleActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i("LifeCycle", "onCreate()");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("LifeCycle", "onStart()");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("LifeCycle", "onResume()");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("LifeCycle", "onPause()");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("LifeCycle", "onStop()");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("LifeCycle", "onDestroy()");
	}

}
