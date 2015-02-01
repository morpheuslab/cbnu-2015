package com.example.chap13.examples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.example.chap13.R;

public class DateTimeTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datetimetest);
		
		Refresh();
	}
	
	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btnrefresh:
			Refresh();
			break;
		}
	}
	
	void Refresh() {
		StringBuilder time = new StringBuilder();

		long epoch = System.currentTimeMillis();
		time.append("epoch = " + epoch + "\n");
		//time.append("now = " + DateUtils.formatDateTime(this, epoch, 0)+ "\n");

		Calendar cal = new GregorianCalendar();
		time.append("now = " + String.format("%d년 %d월 %d일 %d시 %d분\n", 
				cal.get(Calendar.YEAR), 
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH),
				cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)));
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		time.append("now = " + sdf.format(now) + "\n");
		
		Calendar tom = new GregorianCalendar();
		tom.add(Calendar.DAY_OF_MONTH, 1);
		Date tomdate = tom.getTime();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
		time.append("tomorrow = " + sdf2.format(tomdate) + "\n");

		time.append("boot = " + UpTime(SystemClock.elapsedRealtime()));
		time.append("run = " + UpTime(SystemClock.uptimeMillis()));
		time.append("thread = " + UpTime(SystemClock.currentThreadTimeMillis()));

		TextView result = (TextView)findViewById(R.id.result);
		result.setText(time.toString());
	}
	
	String UpTime(long msec) {
		long sec = msec / 1000;
		String result;
		result = String.format("%d일 %d시 %d분 %d초\n", sec / 86400, sec / 3600 % 24, 
				sec / 60 % 60, sec % 60);
		//result = DateUtils.formatElapsedTime(sec) + "\n";
		return result;
	}
}
