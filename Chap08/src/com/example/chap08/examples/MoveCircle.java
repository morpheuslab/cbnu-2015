package com.example.chap08.examples;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;

public class MoveCircle extends ActionBarActivity {
	private MyView vw;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vw = new MyView(this);
		vw.setFocusable(true);
		vw.setFocusableInTouchMode(true);
		setContentView(vw);
	}

	protected class MyView extends View {
		float mX,mY;
		int mColor;

		public MyView(Context context) {
			super(context);
			mX = 100;
			mY = 100;
			mColor = Color.BLUE;
		}

		public void onDraw(Canvas canvas) {
			canvas.drawColor(Color.LTGRAY);
			Paint Pnt = new Paint();
			Pnt.setColor(mColor);
			Pnt.setAntiAlias(true);
			canvas.drawCircle(mX,mY,16,Pnt);
		}

		public boolean onKeyDown(int KeyCode, KeyEvent event) {
			super.onKeyDown(KeyCode, event);
			if (event.getAction() == KeyEvent.ACTION_DOWN) {
				switch (KeyCode) {
				case KeyEvent.KEYCODE_DPAD_LEFT:
					mX-=5;
					invalidate();
					return true;
				case KeyEvent.KEYCODE_DPAD_RIGHT:
					mX+=5;
					invalidate();
					return true;
				case KeyEvent.KEYCODE_DPAD_UP:
					mY-=5;
					invalidate();
					return true;
				case KeyEvent.KEYCODE_DPAD_DOWN:
					mY+=5;
					invalidate();
					return true;
				case KeyEvent.KEYCODE_DPAD_CENTER:
					if (mColor == Color.BLUE) {
						mColor = Color.RED;
					} else {
						mColor = Color.BLUE;
					}
					invalidate();
					return true;
				}
			}
			return false;
		}
	}
}
