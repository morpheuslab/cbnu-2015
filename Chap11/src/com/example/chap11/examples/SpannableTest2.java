package com.example.chap11.examples;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chap11.R;

public class SpannableTest2 extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spannabletest);

		EditText Edit = (EditText)findViewById(R.id.edit);
		Spannable espan = Edit.getText(); 
		espan.setSpan(new StyleSpan(Typeface.ITALIC), 1, 7, 
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		espan.setSpan(new BackgroundColorSpan(0xffff0000), 8, 11, 
				Spannable.SPAN_INCLUSIVE_INCLUSIVE);
		espan.setSpan(new UnderlineSpan(), 12, 17, 
				Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

		TextView Text = (TextView)findViewById(R.id.text);
		Spannable tspan = (Spannable)Text.getText();
		tspan.setSpan(new RelativeSizeSpan(0.5f), 0, 5, 
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tspan.setSpan(new ForegroundColorSpan(0xff0000ff), 5, 9, 
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		tspan.setSpan(new RelativeSizeSpan(1.5f), 9, 12, 
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		TextView link = (TextView)findViewById(R.id.textlink);
		Spannable lspan = (Spannable)link.getText();

		URLSpan profile = new URLSpan("") {
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "이 사람의 프로필을 검색한다.", 0).show();
			}
		};
		lspan.setSpan(profile, 10, 13,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		URLSpan call = new URLSpan("") {
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "이 사람의 연락처를 찾는다.", 0).show();
			}
		};
		lspan.setSpan(call, 18, 21,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		link.setMovementMethod(LinkMovementMethod.getInstance());		
	}
}