package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chap13.R;

public class StopWatch extends Activity {
	TextView mEllapse;
	TextView mSplit;
	Button mBtnStart;
	Button mBtnSplit;
	final static int IDLE = 0;
	final static int RUNNING = 1;
	final static int PAUSE = 2;
	int mStatus = IDLE;
	long mBaseTime;
	long mPauseTime;
	int mSplitCount;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stopwatch);
		
		mEllapse = (TextView)findViewById(R.id.ellapse);
		mSplit = (TextView)findViewById(R.id.split);
		mBtnStart = (Button)findViewById(R.id.btnstart);
		mBtnSplit = (Button)findViewById(R.id.btnsplit);
	}
	
	public void onDestroy() {
		mTimer.removeMessages(0);
		super.onDestroy();
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btnstart:
			switch (mStatus) {
			case IDLE:
				mBaseTime = SystemClock.elapsedRealtime();
				mTimer.sendEmptyMessage(0);
				mBtnStart.setText("중지");
				mBtnSplit.setEnabled(true);
				mStatus = RUNNING;
				break;
			case RUNNING:
				mTimer.removeMessages(0);
				mPauseTime = SystemClock.elapsedRealtime();
				mBtnStart.setText("시작");
				mBtnSplit.setText("초기화");
				mStatus = PAUSE;
				break;
			case PAUSE:
				long now = SystemClock.elapsedRealtime();
				mBaseTime += (now - mPauseTime);
				mTimer.sendEmptyMessage(0);
				mBtnStart.setText("중지");
				mBtnSplit.setText("기록");
				mStatus = RUNNING;
				break;
			}
			break;
		case R.id.btnsplit:
			switch (mStatus) {
			case RUNNING:
				String sSplit = mSplit.getText().toString();
				sSplit += String.format("%d => %s\n", mSplitCount, getEllapse());
				mSplit.setText(sSplit);
				mSplitCount++;
				break;
			case PAUSE:
				mTimer.removeMessages(0);
				mBtnStart.setText("시작");
				mBtnSplit.setText("기록");
				mEllapse.setText("00:00:00");
				mStatus = IDLE;
				mSplitCount = 0;
				mSplit.setText("");
				mBtnSplit.setEnabled(false);
				break;
			}
			break;
		}
	}

	Handler mTimer = new Handler() {
		public void handleMessage(Message msg) {
			mEllapse.setText(getEllapse());
			mTimer.sendEmptyMessage(0);
		}
	};
	
	String getEllapse() {
		long now = SystemClock.elapsedRealtime();
		long ell = now - mBaseTime;
		String sEll = String.format("%02d:%02d:%02d", ell / 1000 / 60, 
				(ell / 1000) % 60, (ell % 1000) / 10);
		return sEll;
	}
}
