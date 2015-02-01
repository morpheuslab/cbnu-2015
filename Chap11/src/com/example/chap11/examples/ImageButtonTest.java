package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.chap11.R;

public class ImageButtonTest extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imagebuttontest);

		ImageButton imgbtn = (ImageButton)findViewById(R.id.imagebtn);
		imgbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(ImageButtonTest.this, "Image Button Clicked", 
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}