package com.example.chap12.examples;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chap12.R;

public class ManyItem extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewtest);

		((ListView) findViewById(R.id.list)).setAdapter(new ManyAdapter(this));
	}
}

class ManyAdapter extends BaseAdapter {
	Context ctx;
	LayoutInflater inflater;
	Toast toast;

	public ManyAdapter(Context context) {
		ctx = context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		return 1000;
	}

	public String getItem(int position) {
		return "" + position;
	}

	public long getItemId(int position) {
		return position;
	}

	// 각 항목의 뷰 생성
	public View getView(int position, View convertView, ViewGroup parent) {
		String log = "position = " + position + ", ";
		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_list_item_1,
					parent, false);
			log += "convertView is null";
		} else {
			log += "convertView is not null";
		}
		Log.d("ManyItem", log);

		if (toast == null) {
			toast = Toast.makeText(ctx, log, Toast.LENGTH_SHORT);
		} else {
			toast.setText(log);
		}
		toast.show();

		TextView txt = (TextView) convertView.findViewById(android.R.id.text1);
		txt.setText("ManyItem ListView : " + position);
		return convertView;
	}
}
