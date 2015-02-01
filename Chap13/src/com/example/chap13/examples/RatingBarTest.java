package com.example.chap13.examples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.chap13.R;

public class RatingBarTest extends Activity {
	RatingBar mRating;
	TextView mRateText;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ratingbartest);

		mRating = (RatingBar)findViewById(R.id.ratingbar);
		mRateText = (TextView)findViewById(R.id.ratetext);

		mRating.setOnRatingBarChangeListener(new 
				RatingBar.OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating, 
					boolean fromUser) {
				mRateText.setText("Now Rate : " + rating);
			}
		});
	}
}