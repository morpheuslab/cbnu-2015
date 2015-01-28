package com.example.chap08.examples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.example.chap08.*;

public class LongClick extends ActionBarActivity {
	int mCount = 0;
	TextView mTextCount;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.longclick);

		mTextCount=(TextView)findViewById(R.id.count);

		findViewById(R.id.decrease).setOnLongClickListener(mLongClickListener);
		findViewById(R.id.increase).setOnLongClickListener(mLongClickListener);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.decrease:
			mCount--;
			mTextCount.setText("" + mCount);
			break;
		case R.id.increase:
			mCount++;
			mTextCount.setText("" + mCount);
			break;
		}
	}

	View.OnLongClickListener mLongClickListener = new View.OnLongClickListener() {
		public boolean onLongClick(View v) {
			switch (v.getId()) {
			case R.id.decrease:
				mCount = 0;
				mTextCount.setText("" + mCount);
				return true;
			case R.id.increase:
				mCount = 100;
				mTextCount.setText("" + mCount);
				break;
			}
			return false;
		}
	};
}