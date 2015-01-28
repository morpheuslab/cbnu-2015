package com.example.chap09.examples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.chap09.R;

public class ChangeMenu extends ActionBarActivity {
	boolean mBeginner = true;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changemenu);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		if (mBeginner) {
			inflater.inflate(R.menu.beginner, menu);
		} else {
			inflater.inflate(R.menu.professional, menu);
		}

		return true;
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btnbegineer:
			mBeginner = true;
			invalidateOptionsMenu();
			break;
		case R.id.btnprofessional:
			mBeginner = false;
			invalidateOptionsMenu();
			break;
		}
	}	
}