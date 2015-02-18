package com.example.mybroadcastsender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MyBroadcastSenderActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button click = (Button) findViewById(R.id.click);
		click.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setAction("com.example.START_WEB");
				sendBroadcast(intent);
				Log.i("MyBroadcastSenderActivity", "sendBroadcast");
			}
		});
	}

    
}