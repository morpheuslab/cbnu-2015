package com.example.musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {
	private static final String TAG = "MusicService";
	MediaPlayer player;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate()");

		player = MediaPlayer.create(this, R.raw.old_pop);
		player.setLooping(false); // Set looping
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Music Service가 중지되었습니다.", Toast.LENGTH_LONG)
				.show();
		Log.d(TAG, "onDestroy()");
		player.stop();
	}

	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "Music Service가 시작되었습니다.", Toast.LENGTH_LONG)
				.show();
		Log.d(TAG, "onStart()");
		player.start();
	}
}
