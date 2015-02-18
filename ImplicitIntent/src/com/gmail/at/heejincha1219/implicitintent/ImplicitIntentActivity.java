package com.gmail.at.heejincha1219.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class ImplicitIntentActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.call:
			intent = new Intent(Intent.ACTION_DIAL,
					Uri.parse("tel:(+82)1012345678"));
			break;
		case R.id.map:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("geo:36.625124,127.457207?z=17"));
			break;
		case R.id.web:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.google.com"));
			break;
		case R.id.contact:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("content://contacts/people/"));
			break;
		case R.id.camera:
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			break;
		}
		if (intent != null) {
			startActivity(intent);
		}
	}

}
