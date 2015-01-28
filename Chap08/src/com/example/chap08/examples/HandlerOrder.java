package com.example.chap08.examples;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class HandlerOrder extends ActionBarActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View vw = new MyView(this);
		//* 리스너 - 1순위
		vw.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					Toast.makeText(HandlerOrder.this,"Listener : Touch Event Received",
							Toast.LENGTH_SHORT).show();
					return true;
				}
				return false;
			}
		});
		//*/
		setContentView(vw);
	}

	protected class MyView extends View {
		public MyView(Context context) {
			super(context);
		}

		//* 뷰의 콜백 메서드 - 2순위
		public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				Toast.makeText(HandlerOrder.this,"View : Touch Event Received",
						Toast.LENGTH_SHORT).show();
				return true;
			}
			return false;
		}
		//*/
	}

	//* 액티비티의 콜백 메서드 - 3순위
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Toast.makeText(HandlerOrder.this,"Activity : Touch Event Received",
					Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}
	//*/
}
