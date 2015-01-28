package com.example.chap08.examples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chap08.*;

/* 핸들러내의 지역 변수로 선언하기
public class HandlerAccess extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handleraccess);

		LinearLayout linear = (LinearLayout)findViewById(R.id.linear); 
		linear.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					TextView text = (TextView)findViewById(R.id.text);
					text.setText("Touched");
					return true;
				}
				return false;
			}
		});
	}
}
//*/

/* 멤버로 선언해 놓기 
public class HandlerAccess extends ActionBarActivity {
	TextView mText;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handleraccess);

		mText = (TextView)findViewById(R.id.text);

		LinearLayout linear = (LinearLayout)findViewById(R.id.linear); 
		linear.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					mText.setText("Touched");
					return true;
				}
				return false;
			}
		});
	}
}
//*/

/* 핸들러를 포함한 메서드의 지역 변수 사용 
public class HandlerAccess extends ActionBarActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handleraccess);

		final TextView outText = (TextView)findViewById(R.id.text);

		LinearLayout linear = (LinearLayout)findViewById(R.id.linear); 
		linear.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					outText.setText("Touched");
					return true;
				}
				return false;
			}
		});
	}
}
//*/

/* 핸들러를 포함한 서브 메서드의 인수 사용 
public class HandlerAccess extends ActionBarActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handleraccess);

		TextView outText = (TextView)findViewById(R.id.text);
		SetTouchHandler(outText);
	}
	
	void SetTouchHandler(TextView tv) {
		final TextView fText = tv;
		
		LinearLayout linear = (LinearLayout)findViewById(R.id.linear); 
		linear.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					fText.setText("Touched");
					return true;
				}
				return false;
			}
		});
	}
}
//*/

//* 핸들러를 포함한서브 메서드의 final 인수 사용 
public class HandlerAccess extends ActionBarActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handleraccess);

		TextView outText = (TextView)findViewById(R.id.text);
		SetTouchHandler(outText);
	}
	
	void SetTouchHandler(final TextView tv) {
		LinearLayout linear = (LinearLayout)findViewById(R.id.linear); 
		linear.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					tv.setText("Touched");
					return true;
				}
				return false;
			}
		});
	}
}
//*/