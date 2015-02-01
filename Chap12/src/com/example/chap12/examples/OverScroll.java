package com.example.chap12.examples;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chap12.R;

public class OverScroll extends Activity {
	ArrayList<String> mItem = new ArrayList<String>();
	ArrayAdapter<String> mAdapter;
	ListView mList;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.overscroll);

		for (int i = 1001; i <= 1003; i++) {
			mItem.add(Integer.toString(i));
		}

		mAdapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, mItem);

		mList = (ListView)findViewById(R.id.list);
		mList.setAdapter(mAdapter);
	}

	public void mOnClick(View v) {
		switch (v.getId()) {
		case R.id.btn3:
			mItem.clear();
			for (int i = 1001; i <= 1003; i++) {
				mItem.add(Integer.toString(i));
			}
			mAdapter.notifyDataSetChanged();
			break;
		case R.id.btn10:
			mItem.clear();
			for (int i = 1001;i <= 1010; i++) {
				mItem.add(Integer.toString(i));
			}
			mAdapter.notifyDataSetChanged();
			break;
		case R.id.btnalways:
			mList.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
			break;
		case R.id.btnnever:
			mList.setOverScrollMode(View.OVER_SCROLL_NEVER);
			break;
		case R.id.btnifscroll:
			mList.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS );
			break;
		}
	}
}

