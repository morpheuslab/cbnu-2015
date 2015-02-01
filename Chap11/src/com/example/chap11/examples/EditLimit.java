package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

import com.example.chap11.R;

public class EditLimit extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editlimit);

		EditText LimitEdit = (EditText)findViewById(R.id.limit);
		LimitEdit.setFilters(new InputFilter[] {
				new InputFilter.LengthFilter(3)
		});
	}
}