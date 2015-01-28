package com.example.chap09.examples;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.example.chap09.R;

public class PopupMenuTest extends ActionBarActivity {
	Button button;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popupmenutest);

		button = (Button)findViewById(R.id.btn);
	}

	public void mOnClick(View v) {
		PopupMenu popup = new PopupMenu(this, v);
		MenuInflater inflater = popup.getMenuInflater();
		Menu menu = popup.getMenu();
		inflater.inflate(R.menu.popupmenutestmenu, menu);
		//popup.inflate(R.menu.popupmenutestmenu);
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.popup_red:
					button.setBackgroundColor(Color.RED);
					break;
				case R.id.popup_green:
					button.setBackgroundColor(Color.GREEN);
					break;
				case R.id.popup_blue:
					button.setBackgroundColor(Color.BLUE);
					break;
				case R.id.popup_black:
					button.setTextColor(Color.BLACK);
					break;
				case R.id.popup_white:
					button.setTextColor(Color.WHITE);
					break;
				case R.id.popup_gray:
					button.setTextColor(Color.GRAY);
					break;
				}
				return false;
			}
		});
		popup.show();
	}	
}
