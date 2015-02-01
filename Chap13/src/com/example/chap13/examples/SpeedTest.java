package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.chap13.R;

public class SpeedTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speedtest);
	}

	public void mOnClick(View v) {
		int i;
		int a, b=123, c=456;
		long start, end;
		
		start = System.currentTimeMillis();
		for (i=0;i<500000000;i++) {
			a=b+c;
		}
		end = System.currentTimeMillis();
		
		TextView result = (TextView)findViewById(R.id.result);
		String sres = "덧셈 5억번에 총 " + (end-start)/1000.0 + " 초가 걸렸습니다.";
		result.setText(sres);
		
	}
}
