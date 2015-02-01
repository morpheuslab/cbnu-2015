package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.chap13.R;

public class SeekBarTest extends Activity {
	SeekBar mSeekBar;
	TextView mVolume;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seekbartest);

		mSeekBar = (SeekBar)findViewById(R.id.seekbar);
		mVolume = (TextView)findViewById(R.id.volume);

		mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, 
					boolean fromUser) {
				mVolume.setText("Now Volume : " + progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}
}