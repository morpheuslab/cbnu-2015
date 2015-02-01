package com.example.chap13.examples;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.example.chap13.R;

public class ScrollViewTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scrollviewtest);

		ScrollView svw = (ScrollView)findViewById(R.id.scr);
		//svw.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
		//svw.setVerticalFadingEdgeEnabled(false);
		//svw.setVerticalScrollBarEnabled(false);
		svw.addView(new ColorView(this));
	}
}

class ColorView extends View {
	public ColorView(Context context) {
		super(context);
	}

	public void onDraw(Canvas canvas) {
		Paint Pnt = new Paint();
		for (int y = 0;y < 2560;y += 10) {
			Pnt.setARGB(255, 255 - y / 10, 255 - y / 10, 255);
			canvas.drawRect(0, y, 2560, y + 10, Pnt);
		}
	}

	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(2560, 2560);
	}
}
