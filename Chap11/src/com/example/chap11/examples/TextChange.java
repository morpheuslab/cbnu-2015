package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chap11.R;

public class TextChange extends Activity {
	EditText mEdit;
	TextView mText;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textchange);

		mEdit = (EditText)findViewById(R.id.edit);
		mText = (TextView)findViewById(R.id.text);
		mEdit.addTextChangedListener(mWatcher);
	}

	TextWatcher mWatcher = new TextWatcher() {
		public void afterTextChanged(Editable s) {
		}

		public void beforeTextChanged(CharSequence s, int start, int count,	int after) {
		}

		public void onTextChanged(CharSequence s, int start, int before, int count) {
			mText.setText("echo:" + s);
		}
	};
}

