package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SlidingDrawer;

import com.example.chap13.R;

public class SlidingDrawerTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slidingdrawertest);
	}

	public void mOnClick(View v) {
		SlidingDrawer drawer = (SlidingDrawer)findViewById(R.id.drawer);
		drawer.animateClose();
	}	
}
