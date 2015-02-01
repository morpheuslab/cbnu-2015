package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.chap11.R;

public class ShowHideKey extends Activity {
	InputMethodManager mImm;
	EditText mEdit;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showhidekey);

		mImm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		mEdit = (EditText)findViewById(R.id.edit);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.show:
			mImm.showSoftInput(mEdit, 0);
			break;
		case R.id.hide:
			mImm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
			break;
		}
	}
}