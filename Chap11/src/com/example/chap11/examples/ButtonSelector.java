package com.example.chap11.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.chap11.R;

public class ButtonSelector extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buttonselector);

		Button btn = (Button)findViewById(R.id.btnexit);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(ButtonSelector.this, "Exit Button Clicked", 
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}