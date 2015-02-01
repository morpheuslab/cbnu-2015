package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.chap13.R;

public class CalendarViewTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendarviewtest);

		CalendarView calendar = (CalendarView)findViewById(R.id.calendar);
		calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth) {
				Toast.makeText(CalendarViewTest.this, "" + year + "/" + 
						(month + 1) + "/" + dayOfMonth, 0).show();
			}
		});
	}
}