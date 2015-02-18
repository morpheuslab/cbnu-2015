package com.gmail.at.heejincha1219.saverestore;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class SaveRestoreActivity extends ActionBarActivity {

	TextView textNums;
	int count = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		textNums = (TextView) findViewById(R.id.text_nums);
		
		if (savedInstanceState != null) {
			count = savedInstanceState.getInt("count");
			textNums.setText("현재 개수 = " + count);
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("count", count);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_inc:
			count ++;
			break;
		case R.id.button_dec:
			if (count > 0) {
				count --;
			}
			break;
		}
		textNums.setText("현재 개수 = " + count);
	}
	
	

}
