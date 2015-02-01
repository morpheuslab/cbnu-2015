package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chap11.R;

public class ArrowButton extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arrowbutton);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.arrow1:
			Toast.makeText(this, "Arrow1 clicked", 0).show();
			break;
		case R.id.arrow2:
			Toast.makeText(this, "Arrow2 clicked", 0).show();
			break;
		case R.id.arrow3:
			Toast.makeText(this, "Arrow3 clicked", 0).show();
			break;
		}
	}    
}