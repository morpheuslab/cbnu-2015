package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chap11.R;

public class EditSelect extends Activity {
	EditText mEdit;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editselect);

		mEdit = (EditText)findViewById(R.id.edit);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.home:
			mEdit.setSelection(0);
			break;
		case R.id.end:
			mEdit.setSelection(mEdit.getText().length());
			break;
		case R.id.selblock:
			mEdit.setSelection(3,10);
			break;
		case R.id.selall:
			mEdit.selectAll();
			break;
		case R.id.getsel:
			int start = mEdit.getSelectionStart();
			int end = mEdit.getSelectionEnd();
			Toast.makeText(EditSelect.this, 
					"start = " + start + ",end = " + end, 
					Toast.LENGTH_LONG).show();
			break;
		}
	}
}