package com.example.musicservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MusicServiceActivity extends Activity implements OnClickListener {
	  private static final String TAG = "MusicService";
	  Button start, stop;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    start = (Button) findViewById(R.id.start);
	    stop = (Button) findViewById(R.id.stop);

	    start.setOnClickListener(this);
	    stop.setOnClickListener(this);
	  }

	  public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.start:
	      Log.d(TAG, "onClick() start ");
	      startService(new Intent(this, MusicService.class));
	      break;
	    case R.id.stop:
	      Log.d(TAG, "onClick() stop");
	      stopService(new Intent(this, MusicService.class));
	      break;
	    }
	  }
	}