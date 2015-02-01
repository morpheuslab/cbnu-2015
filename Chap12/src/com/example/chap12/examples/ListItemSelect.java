package com.example.chap12.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chap12.R;

public class ListItemSelect extends Activity {
	String[] arGeneral = {"김유신", "이순신", "강감찬", "을지문덕"};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewtest);

		ArrayAdapter<String> Adapter;
		Adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, arGeneral);

		ListView list = (ListView)findViewById(R.id.list);
		list.setAdapter(Adapter);
		list.setOnItemClickListener(mItemClickListener);
	}
	
	AdapterView.OnItemClickListener mItemClickListener = 
			new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, 
				int position, long id) {
			String mes;
			mes = "Select Item = " + arGeneral[position];
			Toast.makeText(ListItemSelect.this,mes,Toast.LENGTH_SHORT).show();
		}
	};
}