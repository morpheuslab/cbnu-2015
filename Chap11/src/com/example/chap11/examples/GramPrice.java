package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chap11.R;

public class GramPrice extends Activity {
	EditText mGram, mWon;
	TextView mPrice;
	EditText mGram2, mWon2;
	TextView mPrice2;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gramprice);
		
		mGram = (EditText)findViewById(R.id.gram);
		mWon = (EditText)findViewById(R.id.won);
		mPrice = (TextView)findViewById(R.id.price);
		mGram.addTextChangedListener(mWatcher);
		mWon.addTextChangedListener(mWatcher);

		mGram2 = (EditText)findViewById(R.id.gram2);
		mWon2 = (EditText)findViewById(R.id.won2);
		mPrice2 = (TextView)findViewById(R.id.price2);
		mGram2.addTextChangedListener(mWatcher2);
		mWon2.addTextChangedListener(mWatcher2);
	}

	TextWatcher mWatcher = new TextWatcher() {
		public void afterTextChanged(Editable s) {}
		public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			int gram, won;
			try {
				gram = Integer.parseInt(mGram.getText().toString());
			}
			catch (NumberFormatException e) {
				return;
			}
			try {
				won = Integer.parseInt(mWon.getText().toString());
			}
			catch (NumberFormatException e) {
				return;
			}
			float price = (float)won / gram;
			mPrice.setText(String.format("그램당 %.4f원", price));
		}
	};

	TextWatcher mWatcher2 = new TextWatcher() {
		public void afterTextChanged(Editable s) {}
		public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			int gram, won;
			try {
				gram = Integer.parseInt(mGram2.getText().toString());
			}
			catch (NumberFormatException e) {
				return;
			}
			try {
				won = Integer.parseInt(mWon2.getText().toString());
			}
			catch (NumberFormatException e) {
				return;
			}
			float price = (float)won / gram;
			mPrice2.setText(String.format("그램당 %.4f원", price));
		}
	};
}
