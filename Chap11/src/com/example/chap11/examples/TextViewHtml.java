package com.example.chap11.examples;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.chap11.R;

public class TextViewHtml extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textviewhtml);
		
		TextView text = (TextView)findViewById(R.id.text);
		text.setText(Html.fromHtml(
			"This <b>text</b> is <i>spanned</i> from " + 
			"<u>html</u> <font color='#ff0000'>" + 
			"document</font>"));

		TextView img = (TextView)findViewById(R.id.image);
		img.setText(Html.fromHtml(
			"This is a androboy <img src=\"androboy\"/> image. <img src=\"speechbubble\"/>", 
			new ImageGetter(), null));
	}
	
	public class ImageGetter implements Html.ImageGetter {
		public Drawable getDrawable(String source) {
			int id = 0;
			
			if (source.equals("androboy")) {
				id = R.drawable.androboy;
			} else if (source.equals("speechbubble")) {
				id = R.drawable.speechbubble;
			}
			
			if (id != 0) {
				Drawable drawable = getResources().getDrawable(id);
				drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), 
						drawable.getIntrinsicHeight());
				return drawable;
				
			}
			return null;
		}
	}
}