package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.chap11.R;

public class FilterTouch extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filtertouch);
	}
	
	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btnad:
			Toast.makeText(this, "광고를 보았습니다.", Toast.LENGTH_LONG).show();
			break;
		case R.id.btncard:
			Toast.makeText(this, "물품 대금을 카드 결제합니다.", Toast.LENGTH_LONG).show();
			break;
		case R.id.calltoast:
			LinearLayout linear = (LinearLayout)View.inflate(this, 
					R.layout.faketoast, null);
			Toast fake = new Toast(this);
			fake.setView(linear);
			fake.setGravity(Gravity.START | Gravity.TOP, 0, 20);
			fake.setDuration(Toast.LENGTH_LONG);
			fake.show();
			break;
		}
	}	
}
