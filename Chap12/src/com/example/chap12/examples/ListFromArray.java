package com.example.chap12.examples;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.chap12.R;

public class ListFromArray extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewtest);

		ArrayAdapter<CharSequence> Adapter;
		Adapter = ArrayAdapter.createFromResource(this, R.array.country,
				android.R.layout.simple_list_item_1);

		ListView list = (ListView)findViewById(R.id.list);
		list.setAdapter(Adapter);
	}
}
