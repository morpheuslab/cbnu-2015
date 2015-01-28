package com.example.chap09.examples;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chap09.R;

public class OptionMenu extends ActionBarActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView MyText = new TextView(this);
		MyText.setText("메뉴 키를 누르세요.");
		setContentView(MyText);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		MenuItem item=menu.add(0,1,0,"짜장");
		item.setIcon(R.drawable.ic_launcher);
		item.setAlphabeticShortcut('a');
		//menu.add(0,1,0,"짜장").setIcon(R.drawable.ic_launcher).setAlphabeticShortcut('a');
		
		menu.add(0,2,0,"짬뽕").setIcon(R.drawable.ic_launcher);
		
		SubMenu etc = menu.addSubMenu("기타");
		etc.add(0,3,0,"우동");
		etc.add(0,4,0,"만두");

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(this,"짜장은 달콤해",Toast.LENGTH_SHORT).show();
			return true;
		case 2:
			Toast.makeText(this,"짬뽕은 매워",Toast.LENGTH_SHORT).show();
			return true;
		case 3:
			Toast.makeText(this,"우동은 시원해",Toast.LENGTH_SHORT).show();
			return true;
		case 4:
			Toast.makeText(this,"만두는 공짜야",Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}
}
