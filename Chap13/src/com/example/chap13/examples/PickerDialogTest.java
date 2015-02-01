package com.example.chap13.examples;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.chap13.R;

public class PickerDialogTest extends Activity {
	int mYear, mMonth, mDay, mHour, mMinute;
	TextView mTxtDate;
	TextView mTxtTime;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pickerdialogtest);
		
		mTxtDate = (TextView)findViewById(R.id.txtdate);
		mTxtTime = (TextView)findViewById(R.id.txttime);

		Calendar cal = new GregorianCalendar();
		mYear = cal.get(Calendar.YEAR);
		mMonth = cal.get(Calendar.MONTH);
		mDay = cal.get(Calendar.DAY_OF_MONTH);
		mHour = cal.get(Calendar.HOUR_OF_DAY);
		mMinute = cal.get(Calendar.MINUTE);
		
		UpdateNow();
	}
	
	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btnchangedate:
			new DatePickerDialog(PickerDialogTest.this, mDateSetListener, 
					mYear, mMonth, mDay).show();
			break;
		case R.id.btnchangetime:
			new TimePickerDialog(PickerDialogTest.this, mTimeSetListener, 
					mHour, mMinute, false).show();
			break;
		}
	}
	
	DatePickerDialog.OnDateSetListener mDateSetListener = 
		new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			UpdateNow();
		}            
	};
	
	TimePickerDialog.OnTimeSetListener mTimeSetListener = 
		new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet (TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			UpdateNow();
		}            
	};

	void UpdateNow() {
		mTxtDate.setText(String.format("%d/%d/%d", mYear, 
				mMonth + 1, mDay));
		mTxtTime.setText(String.format("%d:%d", mHour, mMinute));
	}
}
